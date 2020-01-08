
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class ChildParentWords {

    public static HashMap<String, String> data = new HashMap<String, String>();
    public static JPanel buttonPanel = new JPanel();
    public static ArrayList<String> arl = new ArrayList<String>();

    public static void main(String[] args) {
//        crete a new JFrame
        JFrame frame = new JFrame();

//        create a new jpanel
        buttonPanel.setBounds(0, 10, 600, 60);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 600));
        String parent_word = args[0];
//        String parent_word = "SHOPKEEPER";

//        create a jlist
        DefaultListModel model = new DefaultListModel();
        JList childWordsList = new JList(model);
        childWordsList.setBounds(400, 100, 200, 300);

        JLabel messageLabel = new JLabel();
        messageLabel.setBounds(50, 80, 400, 40);
        messageLabel.setBackground(Color.red);

//      close button
        JButton closeButton = new JButton("BACK");
        closeButton.setBounds(690, 15, 90, 40);
        closeButton.addActionListener(e -> {
            Child_parent obj = new Child_parent();
            obj.setVisible(true);
            frame.dispose();
        });

// create a submit button
        JButton submitButton = new JButton("SUBMIT");
        submitButton.setBounds(600, 15, 90, 40);
        submitButton.setEnabled(false);
        submitButton.addActionListener(e -> {
            String name = getTheChildWord();
            if (name.equals("")) {
                messageLabel.setText("The selected word is not a valid English word");
            } else {
                if (model.contains(name)) {
                    messageLabel.setText(name + " is aleady in the list");
                } else {
                    model.addElement(name);
                }
            }
            submitButton.setEnabled(false);
            data.clear();
            buttonPanel.revalidate();
            buttonPanel.repaint();
            enableButtons();
        });

//  create list of buttons from the Prent word using for loop
        for (int i = 0; i < parent_word.length(); i++) {
            char c = parent_word.charAt(i);

//          create individual buttons
            JButton button = new JButton(String.valueOf(c));
            button.putClientProperty("id", String.valueOf(i));
            button.setPreferredSize(new Dimension(50, 50));
            String id = (String) button.getClientProperty("id");
            button.addActionListener(e -> {
                handelButtonEvents(String.valueOf(button.getText()), id);
                button.setEnabled(false);
                submitButton.setEnabled(true);
                messageLabel.setText("");

            });

//          add button to the buttonpanel
            buttonPanel.add(button);

//          add buttonpanel to the main panel 
            panel.add(buttonPanel);

        }
        panel.add(submitButton);
        panel.add(childWordsList);
        panel.add(messageLabel);
        panel.add(closeButton);
        panel.setLayout(null);

        frame.setContentPane(panel);
        frame.pack();
//        frame.size(200,200);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    private static void handelButtonEvents(String text, String index) {
        data.put(index, text);
        System.out.println(data);

    }

    public static void enableButtons() {
        Component[] components = buttonPanel.getComponents();
        for (int i = 0; i < components.length; i++) {
            components[i].setEnabled(true);
        }
    }

    public static ArrayList<Integer> getTheIndexOfTheLetterInWord() {
        ArrayList<Integer> wordKeys = new ArrayList<Integer>();
        for (String key : data.keySet()) {
            wordKeys.add(Integer.parseInt(key));
        }
        return (wordKeys);
    }

    public static boolean checkIndexConsistency() {
        ArrayList test = getTheIndexOfTheLetterInWord();
        Collections.sort(test);
        Integer first = (Integer) test.get(0);
        for (int i = first; i < test.size() - 1; i++) {
            if (test.indexOf(i) + 1 != test.indexOf(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static String getWordValues() {
        String wordValues = "";
        for (String key : data.keySet()) {
            String value = data.get(key);
            wordValues += value;
        }
        return wordValues;
    }

    public void getChildWords(ArrayList<String> childWords) {

        arl = childWords;
        System.out.print(childWords);
    }

    public static String getTheChildWord() {

        String word = getWordValues();
        String return_value;
        boolean isIndexConsistence = checkIndexConsistency();
        boolean is_word = arl.contains(word);

        if (isIndexConsistence && is_word) {
            System.out.println(word + " is a valid child word from Bookmark");
            return_value = word;
        } else {
            System.out.println(word + " is a not a valid child word from Bookmark");
            return_value = "";
        }
        return return_value;
    }
}
