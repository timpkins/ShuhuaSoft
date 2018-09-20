package com.mec.bean;

import java.util.List;

/**
 * @author timpkins
 */
public class Menu extends NetResponse {
    private List<Tab> menu;

    public List<Tab> getMenu() {
        return menu;
    }

    public void setMenu(List<Tab> menu) {
        this.menu = menu;
    }

    public class Tab{
        private String menuname;
        private String menuurl;
        private String menuicon;

        public String getMenuname() {
            return menuname;
        }

        public void setMenuname(String menuname) {
            this.menuname = menuname;
        }

        public String getMenuurl() {
            return menuurl;
        }

        public void setMenuurl(String menuurl) {
            this.menuurl = menuurl;
        }

        public String getMenuicon() {
            return menuicon;
        }

        public void setMenuicon(String menuicon) {
            this.menuicon = menuicon;
        }
    }
}
