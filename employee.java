import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
public class EmployeeSalary extends JFrame implements ActionListener { 
 JLabel name, id, salary, result; 
 JTextField t1, t2, t3; 
 JButton calculate; 
 EmployeeSalary() { 
 setTitle("Employee Salary Calculator");
 setSize(400, 300); 
 setLayout(new GridLayout(5, 2, 10, 10));  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 name = new JLabel("Employee Name:");  id = new JLabel("Employee ID:"); 
 salary = new JLabel("Basic Salary:");  result = new JLabel(); 
 t1 = new JTextField(); 
 t2 = new JTextField(); 
 t3 = new JTextField(); 
 calculate = new JButton("Calculate Salary");  calculate.addActionListener(this); 
 add(name); 
 add(t1); 
 add(id); 
 add(t2); 
 add(salary); 
 add(t3); 
 add(calculate); 
 add(new JLabel("")); 
 add(new JLabel("Result:")); 
 add(result); 
 setVisible(true); 
 } 
 public void actionPerformed(ActionEvent e) {  String n = t1.getText(); 
 String i = t2.getText(); 
 String s = t3.getText(); 
 if (n.isEmpty() || i.isEmpty() || s.isEmpty()) {  result.setText("Fill all fields");  return; 
 }
 try { 
 double basic = Double.parseDouble(s); 
 double hra = basic * 0.20; 
 double da = basic * 0.10; 
 double gross = basic + hra + da; 
 result.setText("<html>Name: " + n + 
 "<br>ID: " + i + 
 "<br>Gross Salary: " + gross + "</html>"); 
 } catch (Exception ex) { 
 result.setText("Enter valid salary"); 
 } 
 } 
 public static void main(String[] args) { 
 new EmployeeSalary(); 
