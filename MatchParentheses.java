import java.util.Stack;

public class MatchParentheses {

    public static void main(String[] args) {
        System.out.println(isMatched("()(()){([()])}"));
        System.out.println(isMatched("((()(()){([()])}))"));
        System.out.println(isMatched(")(()){([()])}"));
        System.out.println(isMatched("({[])}"));
        System.out.println(isMatched("("));
        System.out.println(isMatched("[(5+x)−(y+z)]"));
        System.out.println(isHtmlTagMatched("""
                                                <body>
                                                <center>
                                                <h1> The Little Boat </h1>
                                                </center>
                                                <p> The storm tossed the little
                                                boat like a cheap sneaker in an
                                                old washing machine. The three
                                                drunken fishermen were used to
                                                such treatment, of course, but
                                                not the tree salesman, who even as
                                                a stowaway now felt that he
                                                had overpaid for the voyage. </p>
                                                <ol>
                                                <li> Will the salesman die? </li>
                                                <li> What color is the boat? </li>
                                                <li> And what about Naomi? </li>
                                                </ol>
                                                </body>
                                            """));
    }

    public static boolean isMatched(String expression) {
        Stack<Character> stack = new Stack<>();

        String opening = "[{(";
        String closing = "]})";

        for (char c : expression.toCharArray()) {
            if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '(') {
                stack.push(')');
            } else if (opening.indexOf(c) < 0 && closing.indexOf(c) < 0) {
                continue;
            } else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static boolean isMatched2(String expression) {
        final String opening = "[{(";
        final String closing = "]})";
        LinkedStack<Character> buffer = new LinkedStack<>();

        for (char c : expression.toCharArray()) {
            if (opening.indexOf(c) >= 0) {
                buffer.push(c);
            } else if (closing.indexOf(c) >= 0) {
                if (buffer.isEmpty()) {
                    return false;
                }

                if (opening.indexOf(buffer.pop()) != closing.indexOf(c)) {
                    return false;
                }
            }
        }

        return buffer.isEmpty();
    }

    public static boolean isHtmlTagMatched(String html) {
        LinkedStack<String> buffer = new LinkedStack<>();

        int j = html.indexOf('<');

        while(j != -1) {
            int k = html.indexOf('>', j + 1);
            if(k == -1) {
                return false;
            }

            String tag = html.substring(j + 1, k);
            if(!tag.startsWith("/")) {
                buffer.push(tag);
            } else {
                if(buffer.isEmpty()) {
                    return false;
                }

                if(!tag.substring(1).equals(buffer.pop())) {
                    return false;
                }
            }

            j = html.indexOf('<', k + 1);
        }

        return buffer.isEmpty();

    }
}
