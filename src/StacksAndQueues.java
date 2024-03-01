import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.HashMap;
import java.util.ArrayDeque;

public class StacksAndQueues {

    public StacksAndQueues(){

    }

    public String isBalanced(String s) {
        Stack<Character> brackets = new Stack<>();
        char c;
        char x;

        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                brackets.push(c);
            } else {
                if (brackets.isEmpty()) {
                    return "NO";
                }
                x = brackets.pop();
                if ((c == ')' && x != '(') || (c == ']' && x != '[') || (c == '}' && x != '{')) {
                    return "NO";
                }
            }
        }

        if(brackets.isEmpty()){
            return "YES";
        }else{
            return "NO";
        }
    }

    public String DNAToRNA(String dna){
        Queue<Character> q = new LinkedList<>();
        String rna = "";

        for(int i = 0; i < dna.length(); i++){
            q.add(dna.charAt(i));
        }

        char letter;
        while(!q.isEmpty()){
            letter = q.poll();
            if(letter == 'T'){
                rna += 'U';
            }else{
                rna += letter;
            }
        }
        return rna;
    }

    public String proteins (String rna){
        HashMap<String, String> map = new HashMap<>();
        map.put("UUU", "F");
        map.put("UUC", "F");
        map.put("UUA", "L");
        map.put("UUG", "L");
        map.put("UCU", "S");
        map.put("UCC", "S");
        map.put("UCA", "S");
        map.put("UCG", "S");
        map.put("UAU", "Y");
        map.put("UAC", "Y");
        map.put("UAA", ".");
        map.put("UAG", ".");
        map.put("UGU", "C");
        map.put("UGC", "C");
        map.put("UGA", ".");
        map.put("UGG", "W");

        map.put("CUU", "L");
        map.put("CUC", "L");
        map.put("CUA", "L");
        map.put("CUG", "L");
        map.put("CCU", "P");
        map.put("CCC", "P");
        map.put("CCA", "P");
        map.put("CCG", "P");
        map.put("CAU", "H");
        map.put("CAC", "H");
        map.put("CAA", "Q");
        map.put("CAG", "Q");
        map.put("CGU", "R");
        map.put("CGC", "R");
        map.put("CGA", "R");
        map.put("CGG", "R");

        map.put("AUU", "I");
        map.put("AUC", "I");
        map.put("AUA", "I");
        map.put("AUG", "M");
        map.put("ACU", "T");
        map.put("ACC", "T");
        map.put("ACA", "T");
        map.put("ACG", "T");
        map.put("AAU", "N");
        map.put("AAC", "N");
        map.put("AAA", "K");
        map.put("AAG", "K");
        map.put("AGU", "S");
        map.put("AGC", "S");
        map.put("AGA", "R");
        map.put("AGG", "R");

        map.put("GUU", "V");
        map.put("GUC", "V");
        map.put("GUA", "V");
        map.put("GUG", "V");
        map.put("GCU", "A");
        map.put("GCC", "A");
        map.put("GCA", "A");
        map.put("GCG", "A");
        map.put("GAU", "D");
        map.put("GAC", "D");
        map.put("GAA", "E");
        map.put("GAG", "E");
        map.put("GGU", "G");
        map.put("GGC", "G");
        map.put("GGA", "G");
        map.put("GGG", "G");

        String protein = "";
        Queue<Character> proteinQueue = new ArrayDeque<>();

        String codon;
        String amino;

        for(int i = 0; i < rna.length(); i+= 3){
            codon = rna.substring(i, Math.min(i+3, rna.length()));
            if(map.containsKey(codon)){
                amino = map.get(codon);
                proteinQueue.add(amino.charAt((0)));
            }else{
                proteinQueue.add('.');
            }
        }
        while(!proteinQueue.isEmpty()){
            protein += proteinQueue.poll();
        }

        return protein;
    }

    public String infixToPostfix(String infix){
        Stack<Character> operator = new Stack<>();
        String postfix = "";
        HashMap<Character, Integer> precedence = new HashMap<>();

        precedence.put('^', 3);
        precedence.put('*', 2);
        precedence.put('/', 2);
        precedence.put('+', 1);
        precedence.put('-', 1);

        for (int i = 0; i < infix.length(); i++) {
            char el = infix.charAt(i);
            if (!precedence.containsKey(el) && el != '(' && el != ')') {
                postfix += el;
            } else if (el == '(') {
                operator.push(el);
            } else if (el == ')') {
                while (!operator.isEmpty() && operator.peek() != '(') {
                    postfix += operator.pop();
                }
                operator.pop();
            } else {
                while (!operator.isEmpty() && precedence.getOrDefault(el, 0) <= precedence.getOrDefault(operator.peek(), 0)) {
                    postfix += operator.pop();
                }
                operator.push(el);
            }
        }

        while (!operator.isEmpty()) {
            postfix += operator.pop();
        }

        return postfix.toString();
    }

    public static void main(String[] args) {
        StacksAndQueues s = new StacksAndQueues();

        String[] brackets = {"{[()]}", "{[(])}", "{{[[(())]]}}"};
        String[] rna = {"AGCTGGGAAACGTAGGCCTA", "TTTTTTTTTTGGCGCG", "CTTTGGGACTAGTAACCCATTTCGGCT"};
        String[] proteins = {"AGCUGGGAAACGUAGGCCUA", "UAAGGGAGAGAAGCCACG"};
        String input = "a+b*(c^d-e)^(f+g*h)-i";

        System.out.println("IsBalanced");
        for(String b: brackets){
            System.out.println("Input: " + b);
            System.out.println("Output: " + s.isBalanced(b));
        }

        System.out.println("\nDNA to RNA");
        for (String r : rna) {
            System.out.println("Input: " + r);
            System.out.println("Output: " + s.DNAToRNA(r));
        }

        System.out.println("\nRNA to Proteins");
        for (String p : proteins) {
            System.out.println("Input: " + p);
            System.out.println("Output: " + s.proteins(p));
        }

        System.out.println("\nInfix to Postfix");
        System.out.println("Infix expression: " + input);
        System.out.println("Postfix expression: " + s.infixToPostfix(input));
    }
}
