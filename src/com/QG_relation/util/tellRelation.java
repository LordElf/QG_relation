package com.QG_relation.util;

public class tellRelation {
    public char result(char[] relation){
        if(relation[0] == '爸' && relation[1] == '爸')
            return '兄';
        if(relation[0] == '爸' && relation[1] == '儿')
            return '孙';
        if(relation[0] == '儿' && relation[1] == '爸')
            return '爷';
        if(relation[0] == '老' && relation[1] == '老')
            return '同';
        return 'E';
    }
}
