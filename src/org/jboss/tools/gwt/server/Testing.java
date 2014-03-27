package org.jboss.tools.gwt.server;

/**
 * Created by karthikmarupeddi on 3/25/14.
 */
public class Testing {


    public static void main(String[] args)
    {
        String name ="M/S. PENNAR ENGINEERED BUILDING SYSTEMS LTD.";
        StringBuilder st = new StringBuilder(name);
        if(st.substring(0,4).equalsIgnoreCase("M/S.") )
        {
            st.replace(0,4,"");
            System.out.println(""+st.toString());
        }
    }
}
