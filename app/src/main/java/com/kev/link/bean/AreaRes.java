package com.kev.link.bean;

import java.util.List;

public class AreaRes {

    private String ceaName;
    private String cerAddress;
    private int ceaId;
    private int parentId;
    private String ceaCode;
    private int ceaAreaType;
    private List<AreaRes> areaList;

    public String getCeaName() {
        return ceaName;
    }

    public void setCeaName(String ceaName) {
        this.ceaName = ceaName;
    }

    public String getCerAddress() {
        return cerAddress;
    }

    public void setCerAddress(String cerAddress) {
        this.cerAddress = cerAddress;
    }

    public int getCeaId() {
        return ceaId;
    }

    public void setCeaId(int ceaId) {
        this.ceaId = ceaId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getCeaCode() {
        return ceaCode;
    }

    public void setCeaCode(String ceaCode) {
        this.ceaCode = ceaCode;
    }

    public int getCeaAreaType() {
        return ceaAreaType;
    }

    public void setCeaAreaType(int ceaAreaType) {
        this.ceaAreaType = ceaAreaType;
    }

    public List<AreaRes> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaRes> areaList) {
        this.areaList = areaList;
    }



    public final static String jsonObj = "{\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"ceaName\": \"运营管理本部\",\n" +
            "      \"cerAddress\": null,\n" +
            "      \"ceaId\": 601,\n" +
            "      \"parentId\": 1,\n" +
            "      \"ceaCode\": \"1601001\",\n" +
            "      \"ceaAreaType\": 40,\n" +
            "      \"areaList\": [\n" +
            "        {\n" +
            "          \"ceaName\": \"（省级）公司\",\n" +
            "          \"cerAddress\": null,\n" +
            "          \"ceaId\": 600,\n" +
            "          \"parentId\": 1,\n" +
            "          \"ceaCode\": \"1600001\",\n" +
            "          \"ceaAreaType\": 30,\n" +
            "          \"areaList\": [\n" +
            "            {\n" +
            "              \"ceaName\": \"浦东城市公司\",\n" +
            "              \"cerAddress\": null,\n" +
            "              \"ceaId\": 599,\n" +
            "              \"parentId\": 1,\n" +
            "              \"ceaCode\": \"1599001\",\n" +
            "              \"ceaAreaType\": 20,\n" +
            "              \"areaList\": [\n" +
            "                {\n" +
            "                  \"ceaName\": \"徐汇分公司\",\n" +
            "                  \"cerAddress\": null,\n" +
            "                  \"ceaId\": 2,\n" +
            "                  \"parentId\": 1,\n" +
            "                  \"ceaCode\": \"0201\",\n" +
            "                  \"ceaAreaType\": 10,\n" +
            "                  \"areaList\": [\n" +
            "                    {\n" +
            "                      \"ceaName\": \"第一服务中心（徐汇1组）\",\n" +
            "                      \"cerAddress\": \"徐汇区船厂路101号A幢503室\",\n" +
            "                      \"ceaId\": 3,\n" +
            "                      \"parentId\": 2,\n" +
            "                      \"ceaCode\": \"0001\",\n" +
            "                      \"ceaAreaType\": 1\n" +
            "                    },\n" +
            "                    {\n" +
            "                      \"ceaName\": \"第五十服务中心（普陀）\",\n" +
            "                      \"cerAddress\": \"真北路2500号1616室\",\n" +
            "                      \"ceaId\": 89,\n" +
            "                      \"parentId\": 2,\n" +
            "                      \"ceaCode\": \"0050\",\n" +
            "                      \"ceaAreaType\": 1\n" +
            "                    }\n" +
            "                  ]\n" +
            "                },\n" +
            "                {\n" +
            "                  \"ceaName\": \"自贸区分公司\",\n" +
            "                  \"cerAddress\": null,\n" +
            "                  \"ceaId\": 109,\n" +
            "                  \"parentId\": 1,\n" +
            "                  \"ceaCode\": \"0211\",\n" +
            "                  \"ceaAreaType\": 10,\n" +
            "                  \"areaList\": [\n" +
            "                    {\n" +
            "                      \"ceaName\": \"第十六服务中心（北蔡镇）\",\n" +
            "                      \"cerAddress\": \"莲溪路1099弄20号205室（鹏裕苑）\",\n" +
            "                      \"ceaId\": 28,\n" +
            "                      \"parentId\": 109,\n" +
            "                      \"ceaCode\": \"0016\",\n" +
            "                      \"ceaAreaType\": 1\n" +
            "                    },\n" +
            "                    {\n" +
            "                      \"ceaName\": \"第四十五服务中心（三林镇）\",\n" +
            "                      \"cerAddress\": \"东方路1988号411室华南大厦\",\n" +
            "                      \"ceaId\": 84,\n" +
            "                      \"parentId\": 109,\n" +
            "                      \"ceaCode\": \"0045\",\n" +
            "                      \"ceaAreaType\": 1\n" +
            "                    }\n" +
            "                  ]\n" +
            "                }\n" +
            "              ]\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    public static final String jsonArray = "[\n" +
            "    {\n" +
            "      \"ceaName\": \"运营管理本部\",\n" +
            "      \"cerAddress\": null,\n" +
            "      \"ceaId\": 601,\n" +
            "      \"parentId\": 1,\n" +
            "      \"ceaCode\": \"1601001\",\n" +
            "      \"ceaAreaType\": 40,\n" +
            "      \"areaList\": [\n" +
            "        {\n" +
            "          \"ceaName\": \"（省级）公司\",\n" +
            "          \"cerAddress\": null,\n" +
            "          \"ceaId\": 600,\n" +
            "          \"parentId\": 1,\n" +
            "          \"ceaCode\": \"1600001\",\n" +
            "          \"ceaAreaType\": 30,\n" +
            "          \"areaList\": [\n" +
            "            {\n" +
            "              \"ceaName\": \"浦东城市公司\",\n" +
            "              \"cerAddress\": null,\n" +
            "              \"ceaId\": 599,\n" +
            "              \"parentId\": 1,\n" +
            "              \"ceaCode\": \"1599001\",\n" +
            "              \"ceaAreaType\": 20,\n" +
            "              \"areaList\": [\n" +
            "                {\n" +
            "                  \"ceaName\": \"徐汇分公司\",\n" +
            "                  \"cerAddress\": null,\n" +
            "                  \"ceaId\": 2,\n" +
            "                  \"parentId\": 1,\n" +
            "                  \"ceaCode\": \"0201\",\n" +
            "                  \"ceaAreaType\": 10,\n" +
            "                  \"areaList\": [\n" +
            "                    {\n" +
            "                      \"ceaName\": \"第一服务中心（徐汇1组）\",\n" +
            "                      \"cerAddress\": \"徐汇区船厂路101号A幢503室\",\n" +
            "                      \"ceaId\": 3,\n" +
            "                      \"parentId\": 2,\n" +
            "                      \"ceaCode\": \"0001\",\n" +
            "                      \"ceaAreaType\": 1\n" +
            "                    },\n" +
            "                    {\n" +
            "                      \"ceaName\": \"第五十服务中心（普陀）\",\n" +
            "                      \"cerAddress\": \"真北路2500号1616室\",\n" +
            "                      \"ceaId\": 89,\n" +
            "                      \"parentId\": 2,\n" +
            "                      \"ceaCode\": \"0050\",\n" +
            "                      \"ceaAreaType\": 1\n" +
            "                    }\n" +
            "                  ]\n" +
            "                },\n" +
            "                {\n" +
            "                  \"ceaName\": \"自贸区分公司\",\n" +
            "                  \"cerAddress\": null,\n" +
            "                  \"ceaId\": 109,\n" +
            "                  \"parentId\": 1,\n" +
            "                  \"ceaCode\": \"0211\",\n" +
            "                  \"ceaAreaType\": 10,\n" +
            "                  \"areaList\": [\n" +
            "                    {\n" +
            "                      \"ceaName\": \"第十六服务中心（北蔡镇）\",\n" +
            "                      \"cerAddress\": \"莲溪路1099弄20号205室（鹏裕苑）\",\n" +
            "                      \"ceaId\": 28,\n" +
            "                      \"parentId\": 109,\n" +
            "                      \"ceaCode\": \"0016\",\n" +
            "                      \"ceaAreaType\": 1\n" +
            "                    },\n" +
            "                    {\n" +
            "                      \"ceaName\": \"第四十五服务中心（三林镇）\",\n" +
            "                      \"cerAddress\": \"东方路1988号411室华南大厦\",\n" +
            "                      \"ceaId\": 84,\n" +
            "                      \"parentId\": 109,\n" +
            "                      \"ceaCode\": \"0045\",\n" +
            "                      \"ceaAreaType\": 1\n" +
            "                    }\n" +
            "                  ]\n" +
            "                }\n" +
            "              ]\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]";

    public static final String jsonArray1 = "[\n" +
            "                    {\n" +
            "                      \"ceaName\": \"第十六服务中心（北蔡镇）\",\n" +
            "                      \"cerAddress\": \"莲溪路1099弄20号205室（鹏裕苑）\",\n" +
            "                      \"ceaId\": 28,\n" +
            "                      \"parentId\": 109,\n" +
            "                      \"ceaCode\": \"0016\",\n" +
            "                      \"ceaAreaType\": 1\n" +
            "                    },\n" +
            "                    {\n" +
            "                      \"ceaName\": \"第四十五服务中心（三林镇）\",\n" +
            "                      \"cerAddress\": \"东方路1988号411室华南大厦\",\n" +
            "                      \"ceaId\": 84,\n" +
            "                      \"parentId\": 109,\n" +
            "                      \"ceaCode\": \"0045\",\n" +
            "                      \"ceaAreaType\": 1\n" +
            "                    }\n" +
            "                  ]";
}
