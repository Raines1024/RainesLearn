package com.raines.raineslearn.annotation.custom.project.classAnnotation;

public enum Gender {
    MAN {
        public String getName() {
            return "男";
        }
    },
    WOMEN {
        public String getName() {
            return "女";
        }
    }; // 后面记得有“;”
    public abstract String getName();
}
