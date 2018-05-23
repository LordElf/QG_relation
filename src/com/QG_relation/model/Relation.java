package com.QG_relation.model;

import com.QG_relation.util.Key;
import com.QG_relation.util.tellRelation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Relation {
    public ArrayList<String> fathers, sons, teachers, students;
    public Map<Key, relationType> family_relationMap = new HashMap<>();
    public Map<Key, relationType> teacher_student_relationMap = new HashMap<>();
    public Map<Key, relationType> classmates_relation = new HashMap<>();
    public Stack<Character> relationRecorder = new Stack<>();
    //    public Map<String, HashMap<String,Integer>> father_son_relationMap = new HashMap<>();
    //    private HashMap<String,Integer> tmpMap = new HashMap<>();
    public enum relationType{
        father,
        son,
        grandpa,
        grandson,
        brother,
        elderBro,
        youngerBro,
        teacher,
        student,
        classmates,
        none;
    }

    public Relation(){
        // get info in the text
        ArrayList<String> Info = getInfo();

        fathers = new ArrayList<String>();
        sons = new ArrayList<String>();
        teachers = new ArrayList<String>();
        students = new ArrayList<String>();
        char[] relation;
        ArrayList<Character> keyword;
        String[] people;
        for (String buffer : Info) {
            keyword = new ArrayList<>();
            relation = new char[2];
            people = new String[2];
            int is = buffer.indexOf('是'), first_of = buffer.indexOf('的'), second_of = buffer.lastIndexOf('的');

            if(is < first_of){
                people[0] = buffer.substring(0, is);
                people[1] = buffer.substring(is+1, first_of);
                keyword.add('是');
                keyword.add('的');
                relation[0] = buffer.charAt(first_of+1);
            }
            else if (first_of == second_of){
                people[0] = buffer.substring(0, first_of);
                people[1] = buffer.substring(is+1, buffer.length());
                keyword.add('的');
                keyword.add('是');
                relation[0] = buffer.charAt(first_of+1);
            }
            else{
                people[0] = buffer.substring(0, first_of);
                people[1] = buffer.substring(is+1, second_of);
                keyword.add('的');
                keyword.add('是');
                keyword.add('的');
                relation[0] = buffer.charAt(first_of+1);
                relation[1] = buffer.charAt(second_of+1);
            }
            // tell the relation between p[0] and p[1] in the situation that "xx的xx是xx的xx"
            if(keyword.size() == 3){
                tellRelation tellRelation = new tellRelation();
                char result = tellRelation.result(relation);
                // no relation between two people
                if(result == 'E') {
                    break;
                }
                else relation[0] = result;
                System.out.println(relation[0]);
            } else if(keyword.size()!=2)
                System.out.println("syntax error");

            //change '的' into the same situation as '是'
            if (keyword.size() == 2 && keyword.get(0) == '的') {
                String tmp = people[0];
                people[0] = people[1];
                people[1] = tmp;
            }

            //build relation
            switch (relation[0]){
                case '儿':
                    family_relationMap.put(new Key(people[0],people[1]), relationType.son);
                    family_relationMap.put(new Key(people[1],people[0]), relationType.father);
                    if(!fathers.contains(people[1]))
                        fathers.add(people[1]);
                    if(!sons.contains(people[0]))
                        sons.add(people[0]);
                    break;
                case '爸':
                    family_relationMap.put(new Key(people[0],people[1]), relationType.father);
                    family_relationMap.put(new Key(people[1],people[0]), relationType.son);
                    if(!fathers.contains(people[0]))
                        fathers.add(people[0]);
                    if(!sons.contains(people[1]))
                        sons.add(people[1]);
                    break;
                case '爷':
                    family_relationMap.put(new Key(people[0],people[1]), relationType.grandpa);
                    family_relationMap.put(new Key(people[1],people[0]), relationType.grandson);
                    if(!fathers.contains(people[0]))
                        fathers.add(people[0]);
                    if(!sons.contains(people[1]))
                        sons.add(people[1]);
                    break;
                case '孙':
                    family_relationMap.put(new Key(people[0],people[1]), relationType.grandson);
                    family_relationMap.put(new Key(people[1],people[0]), relationType.grandpa);
                    if(!fathers.contains(people[1]))
                        fathers.add(people[1]);
                    if(!sons.contains(people[0]))
                        sons.add(people[0]);
                    break;
                case '哥':
                    family_relationMap.put(new Key(people[0],people[1]), relationType.elderBro);
                    family_relationMap.put(new Key(people[1],people[0]), relationType.youngerBro);
                    if(!sons.contains(people[0]))
                        sons.add(people[0]);
                    if(!sons.contains(people[1]))
                        sons.add(people[1]);
                    break;
                case '弟':
                    family_relationMap.put(new Key(people[0],people[1]), relationType.youngerBro);
                    family_relationMap.put(new Key(people[1],people[0]), relationType.elderBro);
                    if(!sons.contains(people[0]))
                        sons.add(people[0]);
                    if(!sons.contains(people[1]))
                        sons.add(people[1]);
                    break;
                case '兄':
                    family_relationMap.put(new Key(people[0],people[1]), relationType.brother);
                    family_relationMap.put(new Key(people[1],people[0]), relationType.brother);
                    if(!sons.contains(people[0]))
                        sons.add(people[0]);
                    if(!sons.contains(people[1]))
                        sons.add(people[1]);
                    break;
                case '学':
                    teacher_student_relationMap.put(new Key(people[0],people[1]), relationType.student);
                    teacher_student_relationMap.put(new Key(people[1],people[0]), relationType.teacher);
                    if(!students.contains(people[0]))
                        students.add(people[0]);
                    if(!teachers.contains(people[1]))
                        teachers.add(people[1]);
                    break;
                case '老':
                    teacher_student_relationMap.put(new Key(people[0],people[1]), relationType.teacher);
                    teacher_student_relationMap.put(new Key(people[1],people[0]), relationType.student);
                    if(!students.contains(people[1]))
                        students.add(people[1]);
                    if(!teachers.contains(people[0]))
                        teachers.add(people[0]);
                    break;
                case '同':
                    classmates_relation.put(new Key(people[0],people[1]), relationType.classmates);
                    classmates_relation.put(new Key(people[1],people[0]), relationType.classmates);
                    if(!students.contains(people[1]))
                        students.add(people[1]);
                    if(!students.contains(people[0]))
                        students.add(people[0]);
                    break;
                default : break;
            }
            keyword.clear();
        }
        // this is for test
        System.out.println();
        for(Map.Entry<Key, relationType> entry : family_relationMap.entrySet()){
            Key key = entry.getKey();
            relationType value = entry.getValue();
            System.out.println(key+ "-> " + value);
        }
        System.out.println();
        System.out.println(fathers);
        System.out.println(sons);
        System.out.println(teachers);
        System.out.println(students);
        for(Map.Entry<Key, relationType> entry : teacher_student_relationMap.entrySet()){
            Key key = entry.getKey();
            relationType value = entry.getValue();
            System.out.println(key.toString() + "-> " + value);
        }

        System.out.println(family_relationMap.get(new Key("王羲之", "王凝之")));
        relationType type = family_relationMap.get(new Key("王羲之", "王凝"));
        if(type == null)
            System.out.println(type);
    }


    //get info from people.txt
    public ArrayList<String> getInfo() {
        try{
            BufferedReader input = new BufferedReader(new FileReader("people.txt"));
            ArrayList<String> Info = new ArrayList<String>();
            String str;
            for(int i = 0; (str = input.readLine()) != null;i++)
                Info.add(str);
            System.out.println(Info);
            return Info;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //recursion might help
    public ArrayList<String> getRelation(String[] people){
        ArrayList<String> relationStatements = new ArrayList<>();
        if(people != null) {
            relationStatements.add(people[0] + '&' + people[1] + ':' + '\n');
            if (people.length == 2) {
                relationType type;
                type = searchFamilyRelation(people);
                switch (type) {
                    case father:
                        relationStatements.add(people[0] + "是" + people[1] + "的爸爸");
                        break;
                    case son:
                        relationStatements.add(people[0] + "是" + people[1] + "的儿子");
                        break;
                    case grandpa:
                        relationStatements.add(people[0] + "是" + people[1] + "的爷爷");
                        break;
                    case grandson:
                        relationStatements.add(people[0] + "是" + people[1] + "的孙子");
                        break;
                    case elderBro:
                        relationStatements.add(people[0] + "是" + people[1] + "的哥哥");
                        break;
                    case youngerBro:
                        relationStatements.add(people[0] + "是" + people[1] + "的弟弟");
                        break;
                    case brother:
                        relationStatements.add(people[0] + "是" + people[1] + "的兄弟");
                        break;
                    // if no direct relation between them, tell if there are any relation behind the map,
                    // for instance, they have the same father/son or the same grandpa/grandson
                    default:
                        relationStatements.add("两人不存在上下三代的亲戚关系");
                        break;
                }

                type = searchSchoolRelation(people);
                switch (type) {
                    case teacher:
                        relationStatements.add(people[0] + "是" + people[1] + "的老师");
                        break;
                    case student:
                        relationStatements.add(people[0] + "是" + people[1] + "的学生");
                        break;
                    default :
                        relationStatements.add("两人不存在同学/师生关系");
                        break;
                }
                if((type = classmates_relation.get(new Key(people[0], people[1])))==relationType.classmates)
                    relationStatements.add(people[0] + "是" + people[1] + "的同学");
                return relationStatements;
            }
        }
        relationStatements.add("Input error");
        return relationStatements;
    }

    private relationType searchFamilyRelation(String[] people){
        relationType type;
        type = family_relationMap.get(new Key(people[0], people[1]));
        if(type == null)
            type = searchFamilyRelationImplied(people);
        if(type == null)
            type = relationType.none;
        return type;
    }

    private relationType searchSchoolRelation(String[] people){
        relationType type;
        type = teacher_student_relationMap.get(new Key(people[0], people[1]));
        if(type == null)
            type = searchSchoolRelationImplied(people);
        if(type == null)
            type = relationType.none;

        return type;
    }

    // implying relation in the family_map
    private relationType searchFamilyRelationImplied(String[] people) {
        relationType type;
        // find if they are brothers
        // have to use type = map.get(), because only type can recognize relationType
        if(fathers.contains(people[0]) && fathers.contains(people[1]) ||
                (sons.contains(people[0]) && sons.contains(people[1]))){
            for (String father : fathers)
                if ((type = family_relationMap.get(new Key(people[0], father))) ==
                        (type = family_relationMap.get(new Key(people[1], father))) && type != null)
                    return relationType.brother;
            }
        //if people[0] is the grandpa of people[1]
        //father of p[0] is the son of p[1]
        if(fathers.contains(people[0])&&sons.contains(people[1])){
            for (String son : sons) {
                if ((type = family_relationMap.get(new Key(people[0], son))) == relationType.father
                        && (type = family_relationMap.get(new Key(son, people[1]))) == relationType.father)
                    return relationType.grandpa;
            }
        }
        //if people[0] is the grandson of people[1]
        // son of p[0] is the father of p[1]
        if(sons.contains(people[0])&&fathers.contains(people[1])){
            for (String father : fathers) {
                if ((type = family_relationMap.get(new Key(people[0], father))) == relationType.son
                        && (type = family_relationMap.get(new Key(father, people[1]))) == relationType.son)
                    return relationType.grandson;
            }
        }
        //people[0] is the son of people[1]
        //if brother of people[0] is the son of people[1]
        if(sons.contains(people[0])&&fathers.contains(people[1])){
            for (String brother : sons){
                if((type = family_relationMap.get(new Key(people[0],brother))) == relationType.brother
                        || type == relationType.elderBro || type == relationType.youngerBro) {
                    type = family_relationMap.get(new Key(brother, people[1]));
                    if(type == relationType.son)
                        return relationType.son;
                }
            }
        }
        //people[0] is the father of people[1]
        //if son of people[0] is the brother of people[1]
        if(sons.contains(people[1])&&fathers.contains(people[0])){
            for (String son : sons){
                if((type = family_relationMap.get(new Key(son, people[0]))) == relationType.son)
                    if((type = family_relationMap.get(new Key(people[1],son))) == relationType.brother
                            || type == relationType.elderBro || type == relationType.youngerBro)
                        return relationType.father;
            }
        }
        return relationType.none;
    }
    // implying relation in the teachers_students_Map
    private relationType searchSchoolRelationImplied(String[] people) {
        relationType type;
        String statement = "两人不存在同学/师生关系";
        if(students.contains(people[0]) && students.contains(people[1]))
            for(String teacher : teachers) {
                if ((type = teacher_student_relationMap.get(new Key(people[0], teacher))) == relationType.student
                        && (type = teacher_student_relationMap.get(new Key(people[1], teacher))) == relationType.student)
                    return relationType.classmates;
            }
        return null;
    }

    public String[] tellStr(String str) {
        if(str.equals(""))
            return null;
        String[] people = new String[2];
        int firstRecord = 0, secondRecord = 0;
        secondRecord = str.lastIndexOf(' ');
        if(secondRecord != -1) {
            people[0] = str.substring(firstRecord, secondRecord);
            people[1] = str.substring(secondRecord + 1, str.length());
            return people;
        }
        // this part is for appended part
        else{
            secondRecord = str.lastIndexOf('是');
        }
        return null;
    }

}
