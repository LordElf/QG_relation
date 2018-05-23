package com.QG_relation.util;

public class tellRelation {
    public static char result(char[] relation){
        if(relation[0] == '爸' && relation[1] == '爸'
                || relation[0] == '哥' && relation[1] == '哥'
                || relation[0] =='弟' &&relation[1] =='弟')
            return '兄';
        if(relation[0] =='哥' && relation[1] == '弟')
            return '弟';
        if(relation[0] =='弟' && relation[1] == '哥')
            return '哥';
        if((relation[0] =='兄' ||relation[0] =='哥' ||relation[0] =='弟')&& relation[1] == '儿')
            return '儿';
        if(relation[0] =='儿' && (relation[1] =='兄' ||relation[1] =='哥' ||relation[1] =='弟'))
            return '爸';
        if(relation[0] == '爸' && relation[1] == '儿')
            return '孙';
        if(relation[0] == '儿' && relation[1] == '爸')
            return '爷';
        if(relation[0] == '老' && relation[1] == '老')
            return '同';
        return 'E';
    }
}
