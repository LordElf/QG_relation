package com.QG_relation.util;

import com.QG_relation.model.Relation;

public class deleteRelation {
    public String delete(Relation relation, String[] people){
        Relation.relationType type1, type2, type3;
        Key key = new Key(people[0], people[1]);
        Key keyInverse = new Key((people[1]), people[0]);
        type1 = relation.family_relationMap.get(key);
        if(type1 != null) {
            relation.family_relationMap.remove(key);
            relation.family_relationMap.remove(keyInverse);
        }
        type2 = relation.teacher_student_relationMap.get(key);
        if(type2 != null) {
            relation.teacher_student_relationMap.remove(key);
            relation.teacher_student_relationMap.remove(keyInverse);
        }
        type3 = relation.classmates_relation.get(key);
        if(type3 != null) {
            relation.classmates_relation.remove(key);
            relation.classmates_relation.remove(keyInverse);
        }
        if(null == type1 && null == type2 && null == type3)
            return("The relation cannot be deleted or not exists");
        return ("Delete succeeded");
    }
}
