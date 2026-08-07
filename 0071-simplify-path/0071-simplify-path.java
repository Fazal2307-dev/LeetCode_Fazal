class Solution {

    public String simplifyPath(String path) {

        String[] components = path.split("/");

        Stack<String> st = new Stack<>();

        for (String comp : components) {

            // Empty component or current directory
            // changes nothing
            if (comp.equals("") || comp.equals(".")) {
                continue;
            }

            // Go back to parent directory
            if (comp.equals("..")) {

                // Cannot go above root
                if (!st.isEmpty()) {
                    st.pop();
                }

            } else {

                // Normal directory
                st.push(comp);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!st.isEmpty()) {
            sb.insert(0, "/" + st.pop());
        }

        // If no directory exists,
        // canonical path is root "/"
        return sb.length() == 0 ? "/" : sb.toString();
    }
}