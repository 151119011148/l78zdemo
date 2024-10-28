package com.scofen.util.sql.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForeignKey {

    private String keyName;

    private String currentTable;

    private String currentTableFieldName;

    private String relateTable;

    private String relateTableFieldName;


    public String getKeyName(){
        String keyName = "fk_" + currentTable + "_" + relateTableFieldName;
        if (keyName.length() > 63){
            keyName = convertToFkCaArAtId(keyName);
        }
        return keyName;
    }

    public static String convertToFkCaArAtId(String input) {
        StringBuilder sb = new StringBuilder();
        String[] parts = input.split("_");
        for (String part : parts) {
            sb.append(part.charAt(0));
        }
        return sb.toString();
    }

}
