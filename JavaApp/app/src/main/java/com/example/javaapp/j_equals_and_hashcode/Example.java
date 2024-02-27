package com.example.javaapp.j_equals_and_hashcode;

public class Example {
    public static void main(String[] args) throws CloneNotSupportedException {
        Child child1 = new Child("0", 10);
        Child child2 = new Child("1", 11);
        Child child3 = new Child("2", 12);
        Child child4 = new Child("3", 13);

        Child[] children = {child1, child2, child3, child4};
        ChildrenGarden childrenGarden = new ChildrenGarden(children);

        boolean result = false;
        Child findChild = new Child("1", 11);

        for (Child child : childrenGarden.getChildren()) {
            if (child.equals(findChild)) {
                result = true;
                break;
            }
        }
        //-----------------
        Child childX = new Child("x", 0);
        Child clone = (Child) childX.clone();
        make1000clones(clone);
    }

    public static void make1000clones(Child child) throws CloneNotSupportedException {
        Child[] children1000 = new Child[1000];
        for (int i = 0; i < 1000; i++) {
            children1000[i] = (Child) child.clone();
        }
    }
}
