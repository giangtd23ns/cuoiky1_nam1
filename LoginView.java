package view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ActionListener{
    private JLabel userNameLabel;
    private JLabel passWordLabel;
    private JPasswordField passWordField;
    private JTextField userNameField;
    private JButton loginButton;
    private JButton exitButton;
    private Choice choice;
    
    public LoginView(){
        userNameLabel = new JLabel("Tên Đăng Nhập ");
        passWordLabel = new JLabel("Mật Khẩu ");
        
        userNameField = new JTextField(15);
        passWordField = new JPasswordField(15);
        
        choice = new Choice();
        choice.setBounds(100, 100, 130, 25);
        choice.add("Đại lý cấp 1");
        choice.add("Đại lý cấp 2");
        
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {	
			@Override
			 public void actionPerformed(ActionEvent e) {
		    	String enterUserName=userNameField.getText();
		    	String enterPassWord= new String(passWordField.getPassword());
		    	String enterChoice = choice.getSelectedItem();
		    		if(enterUserName.equals("duonggiang") && enterPassWord.equals("duonggiang") && enterChoice.equals("Đại lý cấp 1")){ 
		    			new TicketView("LOTTERY TICKETS REVENUE MANAGEMENT");
		    			dispose();
		    		} else if(enterUserName.equals("root") && enterPassWord.equals("duonggiang") && enterChoice.equals("Đại lý cấp 2")){
		    			new TicketView2("LOTTERY TICKETS REVENUE MANAGEMENT");
		    			dispose();
		    		} else if(enterUserName.equals("") && !enterPassWord.equals("")){
		    			JOptionPane.showMessageDialog(LoginView.this, "Vui lòng nhập Tên đăng nhập!", "Error", JOptionPane.ERROR_MESSAGE);
		    		} else if(!enterUserName.equals("") && enterPassWord.equals("")){
		    			JOptionPane.showMessageDialog(LoginView.this, "Vui lòng nhập Mật khẩu đăng nhập!", "Error", JOptionPane.ERROR_MESSAGE);
		    		} else if(enterUserName.equals("") && enterPassWord.equals("")){
		    			JOptionPane.showMessageDialog(LoginView.this, "Vui lòng nhập thông tin đăng nhập!", "Error", JOptionPane.ERROR_MESSAGE);
		    		} else{
		    			JOptionPane.showMessageDialog(LoginView.this, "Tên đăng nhập hoặc mật khẩu của bạn không hợp lệ!  Vui lòng nhập lại!", "Error", JOptionPane.ERROR_MESSAGE);
		    		}
		    }
		});
        
        exitButton = new JButton("Cancel");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
 
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(400, 250);
        panel.setLayout(layout);
        panel.add(choice);
        panel.add(userNameLabel);
        panel.add(passWordLabel);
        panel.add(userNameField);
        panel.add(passWordField);
        panel.add(loginButton);
        panel.add(exitButton);
 
        layout.putConstraint(SpringLayout.WEST, choice, 160, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.SOUTH, choice, -30, SpringLayout.SOUTH, userNameLabel);
        layout.putConstraint(SpringLayout.WEST, userNameLabel, 60, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, userNameLabel, 80, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passWordLabel, 60, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passWordLabel, 105, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, userNameField, 100, SpringLayout.WEST, userNameLabel);
        layout.putConstraint(SpringLayout.NORTH, userNameField, 80, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passWordField, 100, SpringLayout.WEST, passWordLabel);
        layout.putConstraint(SpringLayout.NORTH, passWordField, 105, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, loginButton, 40, SpringLayout.WEST, passWordLabel);
        layout.putConstraint(SpringLayout.NORTH, loginButton, 150, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, exitButton, 120, SpringLayout.WEST, loginButton);
        layout.putConstraint(SpringLayout.NORTH, exitButton, 150, SpringLayout.NORTH, panel);
 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setTitle("LOGIN");
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	public static void main(String[] args){
		new LoginView();
	} 
}
