package com.hc.comm;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author yusj
 * @date 2018/5/7
 */
public class XmlParseUtil {


    private final BufferedWriter buf;
    private final FileWriter  fw;
    private final Set set;
    private String inPath;
    private final Hcounter recordCounter;
    private final Hcounter openCounter;

    /**
     *
     * @param fw  outfile
     * @param set IpRecord
     * @param inPath infile
     */
    public static XmlParseUtil  create(FileWriter fw, Set set,String inPath,
                                       Hcounter recordCounter,
                                       Hcounter openCounter){
        return new XmlParseUtil(fw,set,inPath,recordCounter,openCounter);
    }


    /**
     *
     * @param fw  outfile
     * @param set IpRecord
     * @param inPath infile
     */
    public XmlParseUtil(FileWriter fw, Set set, String inPath, Hcounter recordCounter,
                        Hcounter openCounter) {
        this.fw=fw;
        buf=new BufferedWriter(fw);
        this.set = set;
        this.inPath=inPath;
        this.recordCounter=recordCounter;
        this.openCounter=openCounter;
    }



    /**
     * @return {command,up,down,total}
     * @throws DocumentException
     */
    public String[] parseXml() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document=null;
        try{

            reader.addHandler("/nmaprun/host", new ElementHandler() {
                @Override
                public void onStart(ElementPath elementPath) {

                }

                @Override
                public void onEnd(ElementPath elementPath) {
                    Element host=elementPath.getCurrent();
                    StringBuilder  stringBuilder=new StringBuilder();

                    String addr=host.selectSingleNode("./address[@addrtype='ipv4']").
                            selectSingleNode("./@addr").getStringValue();

                    stringBuilder.append(addr).append(",");



                    List<Node> ports=host.selectNodes("./ports/port");
                    boolean isOpen=false;
                    for(Node portNode:ports){

                        String state=portNode.selectSingleNode("./state/@state")==null?"unkown":
                                portNode.selectSingleNode("./state/@state").getStringValue();
                        String serviceName=portNode.selectSingleNode("./service/@name")==null?"unkown"
                                :portNode.selectSingleNode("./service/@name").getStringValue();
                        if(state.contains("open")){
                            isOpen=true;
                        }

                        stringBuilder.append(portNode.selectSingleNode("./@portid").getStringValue())
                                .append("/")
                                .append(state)
                                .append("/")
                                .append(serviceName)
                                .append(";");

                    }

                    if(set!=null&&set.size()>0){
                        stringBuilder.append(",");
                        if(set.contains(addr)){
                            stringBuilder.append("Y");
                            recordCounter.add();
                        }else{
                            stringBuilder.append("N");
                        }

                    }

                    stringBuilder.append(",");
                    if(isOpen){
                        stringBuilder.append("Y");
                        openCounter.add();
                    }else{
                        stringBuilder.append("N");
                    }

                    try {
                        buf.write(stringBuilder.toString());
                        buf.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    host.detach();
                }
            });

            document = reader.read(inPath);

            buf.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(buf!=null){
                try {
                    buf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        String command="";
        String up=document.selectSingleNode("/nmaprun/runstats/hosts/@up").getStringValue();
        String down=document.selectSingleNode("/nmaprun/runstats/hosts/@down").getStringValue();
        String total=document.selectSingleNode("/nmaprun/runstats/hosts/@total").getStringValue();

        System.out.println("command:"+command);
        System.out.println("up:"+up);
        System.out.println("down:"+down);
        System.out.println("total:"+total);

        return new String[]{command,up,down,total,
                recordCounter.getCount()+"",openCounter.getCount()+""};
       //return document.asXML();
    }

}
