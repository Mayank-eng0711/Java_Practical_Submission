import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
public class StudentRegistration extends JFrame implements ActionListener { 
 JLabel name, roll, marks, result; 
 JTextField t1, t2, t3; 
 JButton submit; 
 StudentRegistration() { 
 setTitle("Student Registration"); 
 setSize(400, 300); 
 setLayout(new GridLayout(5, 2, 10, 10)); 
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 name = new JLabel("Name:"); 
 roll = new JLabel("Roll Number:"); 
 marks = new JLabel("Marks:"); 
 result = new JLabel();
 t1 = new JTextField(); 
 t2 = new JTextField(); 
 t3 = new JTextField(); 
 submit = new JButton("Submit"); 
 submit.addActionListener(this); 
 add(name); 
 add(t1); 
 add(roll); 
 add(t2); 
 add(marks); 
 add(t3); 
 add(submit); 
 add(new JLabel("")); 
 add(new JLabel("Result:")); 
 add(result); 
 setVisible(true); 
 } 
 public void actionPerformed(ActionEvent e) {  String n = t1.getText(); 
 String r = t2.getText(); 
 String m = t3.getText(); 
 if (n.isEmpty() || r.isEmpty() || m.isEmpty()) {  result.setText("Fill all fields");  return; 
 } 
 try { 
 double marks = Double.parseDouble(m);  String grade; 
 if (marks >= 90) 
 grade = "A+"; 
 else if (marks >= 80) 
 grade = "A"; 
 else if (marks >= 70)
 grade = "B"; 
 else if (marks >= 60) 
 grade = "C"; 
 else if (marks >= 50) 
 grade = "D"; 
 else if (marks >= 40) 
 grade = "E"; 
 else 
 grade = "F"; 
 result.setText("<html>" + n + "<br>" + r + "<br>" + grade + "</html>"); 
 } catch (Exception ex) { 
 result.setText("Enter valid marks"); 
 } 
 } 
 public static void main(String[] args) { 
 new StudentRegistration(); 
 } 
}
