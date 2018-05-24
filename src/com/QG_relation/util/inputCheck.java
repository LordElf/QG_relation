package com.QG_relation.util;

import com.QG_relation.model.Relation;

import java.util.Map;

public class inputCheck {
    public enum checkResult{
        no_relation,
        relation_existed,
        relation_crashed;
    }
    public static checkResult relationCheck(String[] people, Relation.relationType relation, Relation _relation){
        Relation.relationType type1, type2;
        type1 = _relation.searchFamilyRelation(people);
        if(type1 == relation)
            return checkResult.relation_existed;
        type2 = _relation.searchFamilyRelation(people);
        if(type1 == Relation.relationType.none && type2 == Relation.relationType.none)
            return checkResult.no_relation;
        if(type2 == relation)
            return checkResult.relation_existed;
        return checkResult.relation_crashed;
    }
}
