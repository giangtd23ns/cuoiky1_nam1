package view;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.Statement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class SearchResult2 extends JFrame {
    public SearchResult2(Vector<Vector<String>> searchData, Vector<String> columnNames) {
        super("Search Result");

        DefaultTableModel model = new DefaultTableModel(searchData, columnNames);
        JTable searchResultTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(searchResultTable);

        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
 
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

class SearchForm2 extends JFrame {
	JLabel searchLabel;
    JTextField searchField;
    ActionListener searchActionListener;

    public SearchForm2(ActionListener searchActionListener) {
        super("Search Form");

        this.searchActionListener = searchActionListener; 
        
        searchLabel = new JLabel("Vui lòng nhập thông tin cần tìm kiếm (Ngày nhập):");
        searchField = new JTextField(15);
        
        JButton searchButton = new JButton("OK");
        searchButton.addActionListener(e -> {
            if (this.searchActionListener != null) {
                this.searchActionListener.actionPerformed(
                        new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Search"));
            }
        });
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        layout.putConstraint(SpringLayout.WEST, searchLabel, 20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, searchLabel, 20, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchField, 60, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, searchField, 50, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchButton, 55, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, searchButton, 80, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, cancelButton, 100, SpringLayout.WEST, searchButton);
        layout.putConstraint(SpringLayout.NORTH, cancelButton, 80, SpringLayout.NORTH, this);
        
        this.add(searchLabel);
        this.add(searchField);
        this.add(searchButton);
        this.add(cancelButton);

        this.setSize(300, 200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
    }

    public String getSearchText() {
        return searchField.getText().trim();
    }

    public void setSearchActionListener(ActionListener searchActionListener) {
        this.searchActionListener = searchActionListener;
    }
}

class Form extends JFrame implements ActionListener{
	private JLabel idLabel;
	private JLabel dateLabel;
	private JLabel ticketEnteredLabel;
	private JLabel ticketSoldLabel;

	private JTextField idField;
	private JTextField dateField;
	private JTextField ticketEnteredField;
	private JTextField ticketSoldField;
	
	private JLabel errorLabel;
	private JLabel errorDetails;
	 
	private JButton ok;
	private JButton cancel;

	TicketView2 mfr;
	
	public Form (String title, TicketView2 mf, String id, String date, String entered, String sold) {
		super(title);
		mfr = mf;
		
		JPanel p = new JPanel();
		SpringLayout layout = new SpringLayout();
		p.setLayout(layout);
		p.setSize(350, 300);
		
		idLabel = new JLabel("Mã lô nhập");
		idField = new JTextField(10);
		p.add(idLabel);
		p.add(idField);
		
		dateLabel = new JLabel("Ngày nhập");
		dateField = new JTextField(15);
		p.add(dateLabel);
		p.add(dateField);
		
		ticketEnteredLabel = new JLabel("Số vé nhập vào");
		ticketEnteredField = new JTextField(10);
		p.add(ticketEnteredLabel);
		p.add(ticketEnteredField);
		
		ticketSoldLabel = new JLabel("Số vé phân phối");
		ticketSoldField = new JTextField(10);
		p.add(ticketSoldLabel);
		p.add(ticketSoldField);
		
		errorLabel = new JLabel("");
		errorDetails = new JLabel("");
		errorLabel.setVisible(false);
		errorDetails.setVisible(false);
		p.add(errorLabel);
		p.add(errorDetails);
		
		
		
		JButton ok = new JButton("OK");
		JButton cancel=new JButton("Cancel");
		p.add(ok);
		p.add(cancel);
		ok.addActionListener(this);
		cancel.addActionListener(this);
		
		layout.putConstraint(SpringLayout.WEST, idLabel, 30, SpringLayout.WEST, p);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, p);
        layout.putConstraint(SpringLayout.WEST, dateLabel, 30, SpringLayout.WEST, p);
        layout.putConstraint(SpringLayout.NORTH, dateLabel, 45, SpringLayout.NORTH, p);
        layout.putConstraint(SpringLayout.WEST, ticketEnteredLabel, 30, SpringLayout.WEST, p);
        layout.putConstraint(SpringLayout.NORTH, ticketEnteredLabel, 80, SpringLayout.NORTH, p);
        layout.putConstraint(SpringLayout.WEST, ticketSoldLabel, 30, SpringLayout.WEST, p);
        layout.putConstraint(SpringLayout.NORTH, ticketSoldLabel, 115, SpringLayout.NORTH, p);
        layout.putConstraint(SpringLayout.WEST, errorLabel, 70, SpringLayout.WEST, p);
        layout.putConstraint(SpringLayout.NORTH, errorLabel, 230, SpringLayout.NORTH, p);
        
        layout.putConstraint(SpringLayout.WEST, idField, 150, SpringLayout.WEST, p);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, p);
        layout.putConstraint(SpringLayout.WEST, dateField, 150, SpringLayout.WEST, p);
        layout.putConstraint(SpringLayout.NORTH, dateField, 45, SpringLayout.NORTH, p);
        layout.putConstraint(SpringLayout.WEST, ticketEnteredField, 150, SpringLayout.WEST, p);
        layout.putConstraint(SpringLayout.NORTH, ticketEnteredField, 80, SpringLayout.NORTH, p);
        layout.putConstraint(SpringLayout.WEST, ticketSoldField, 150, SpringLayout.WEST, p);
        layout.putConstraint(SpringLayout.NORTH, ticketSoldField, 115, SpringLayout.NORTH, p);
        layout.putConstraint(SpringLayout.WEST, errorDetails, 120, SpringLayout.WEST, p);
        layout.putConstraint(SpringLayout.NORTH, errorDetails, 230, SpringLayout.NORTH, p);
        
        layout.putConstraint(SpringLayout.WEST, ok, 90, SpringLayout.WEST, p);
        layout.putConstraint(SpringLayout.NORTH, ok, 170, SpringLayout.NORTH, p);
        layout.putConstraint(SpringLayout.WEST, cancel, 100, SpringLayout.WEST, ok);
        layout.putConstraint(SpringLayout.NORTH, cancel, 170, SpringLayout.NORTH, p);
       
		this.add(p);
		this.setSize(350, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("OK")) {
			insertDB();
		}
		else this.dispose();
	}
	
	public void insertDB() {
		try {
		int enteredTickets = Integer.parseInt(ticketEnteredField.getText().trim());
		int soldTickets = Integer.parseInt(ticketSoldField.getText().trim());
		
		String date1 = dateField.getText();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate.parse(date1, formatter);
		
		if(idField.getText().equals("") || dateField.getText().equals("") || ticketEnteredField.getText().equals("") || ticketSoldField.getText().equals("")) {
			errorLabel.setText("Lỗi: ");
			errorDetails.setText("Chưa nhập đủ thông tin!");
			errorLabel.setForeground(Color.RED);
			
			errorLabel.setVisible(true);
			errorDetails.setVisible(true);
		} else if(soldTickets > enteredTickets) {
			errorLabel.setText("Lỗi: ");
			errorDetails.setText("Giá trị vé phân phối lớn hơn!");
			errorLabel.setForeground(Color.RED);
			
			errorLabel.setVisible(true);
			errorDetails.setVisible(true);
			ticketSoldField.requestFocus();
		} else if(enteredTickets < 1000) {
			errorLabel.setText("Lỗi: ");
			errorDetails.setText("Số lượng vé nhập tối thiểu: 1000");
			errorLabel.setForeground(Color.RED);
			
			errorLabel.setVisible(true);
			errorDetails.setVisible(true);
			ticketEnteredField.requestFocus();
		} else if(enteredTickets > 4000) {
			errorLabel.setText("Lỗi: ");
			errorDetails.setText("Số vé nhập vượt hạn mức cho phép!");
			errorLabel.setForeground(Color.RED);
			
			errorLabel.setVisible(true);
			errorDetails.setVisible(true);
			ticketEnteredField.requestFocus();
		} else {
				String id  =idField.getText();
				String date  = dateField.getText();
				int entered = Integer.parseInt(ticketEnteredField.getText());
				int sold = Integer.parseInt(ticketSoldField.getText());
				
				if(this.getTitle().equals("Insert Form")) 
					mfr.insertToDatabase(id, date, entered, sold, ((double)(entered-sold)*100)/entered, (sold*9300)-(entered*8000));
				else mfr.updateInDatabase(id, date, entered, sold, ((double)(entered-sold)*100)/entered, (sold*9300)-(entered*8000));
				
				this.dispose();
		}
		} catch(DateTimeParseException e) {
			errorLabel.setText("Lỗi: ");
			errorDetails.setText("Ngày sai định dạng: yyyy-mm-dd");
			errorLabel.setForeground(Color.RED);
			
			errorLabel.setVisible(true);
			errorDetails.setVisible(true);
			dateField.requestFocus();
			
		} catch(NumberFormatException e) {
			errorLabel.setText("Lỗi: ");
			errorDetails.setText("Số sai định dạng!");
			errorLabel.setForeground(Color.RED);
			
			errorLabel.setVisible(true);
			errorDetails.setVisible(true);
			
		}
	}
	
}

public class TicketView2 extends JFrame implements ActionListener, MouseListener{
	Vector vData = new Vector();
	Vector vTitle = new Vector();
	
	JScrollPane tableResult;
	DefaultTableModel model;
	JTable tb = new JTable();
	
	JButton edit, delete, insert, search;
	
	int selectedrow = 0;
	String[] colums = {"Mã lô nhập", "Ngày nhập", "Số vé nhập vào", "Số vé phân phối", "Tỉ lệ vé dư (%)", "Doanh thu"};
	String[][] Data = {};
	
	String JDBC_URL = "jdbc:mysql://localhost:3306/ticketmanagement";
	String DBUserName = "root";
	String DBPass = "2gp5fq4O!";
	String driver = "com.mysql.jdbc.Driver";
	Connection connection;
	
	public TicketView2 (String s) {
		super(s);
		try {
			
			connection = DriverManager.getConnection(JDBC_URL, DBUserName, DBPass);
			System.out.println("Connected!");
			
			JPanel p = new JPanel();
			
			edit = new JButton("Edit");
			edit.addActionListener(this);
			
			delete = new JButton("Delete");
			delete.addActionListener(this);
			
			insert = new JButton("Insert");
			insert.addActionListener(this);
			
			search = new JButton("Search");
			search.addActionListener(this);

			p.add(edit);
			p.add(delete);
			p.add(insert);
			p.add(search);
			
			this.add(p, "South");
			load();
			model = new DefaultTableModel(vData, vTitle);
			tb  = new JTable(model);
			tb.addMouseListener(this);
			
			tableResult  = new JScrollPane(tb);
			
			this.getContentPane().add(tableResult, "North");
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(400, 300);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			
			loadFromDatabase();
			
			} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Unable to connect!");
		}
	}
	
	public void loadFromDatabase() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM dai_ly_cap_2");

            while (resultSet.next()) {
                String id = resultSet.getString("ma_lo_nhap");
                String date = resultSet.getString("ngay_nhap");
                int entered = resultSet.getInt("so_ve_nhap_vao");
                int sold = resultSet.getInt("so_ve_phan_phoi");
                double left = resultSet.getDouble("ti_le_ve_du");
                int revenue = resultSet.getInt("doanh_thu");

                insertList(id, date, entered, sold, left, revenue);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void insertToDatabase(String id, String date, int entered, int sold, double left, int revenue) {
        try {
            Statement statement = connection.createStatement();
            String insertQuery = "INSERT INTO dai_ly_cap_2 (ma_lo_nhap, ngay_nhap, so_ve_nhap_vao, so_ve_phan_phoi, ti_le_ve_du, doanh_thu) VALUES ('" + id + "', '" + date + "', " + entered + ", " + sold + "," + (((double)(entered - sold) * 100) / entered) + ", " + ((sold*9300)-(entered*8000)) + ")";
            statement.executeUpdate(insertQuery);
            statement.close();

            insertList(id, date, entered, sold, ((double)(entered - sold) * 100) / entered, (sold * 9300)-(entered * 8000));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Không thể thêm dữ liệu vào MySQL!");
        }
    }
	
	public void updateInDatabase(String id, String date, int entered, int sold, double left, int revenue) {
        try {
            Statement statement = connection.createStatement();
            String updateQuery = "UPDATE dai_ly_cap_2 SET ngay_nhap = '" + date + "', so_ve_nhap_vao = " + entered + ", so_ve_phan_phoi = " + sold + ", ti_le_ve_du = " + (((double)(entered - sold) * 100) / entered) + ", doanh_thu = " +((sold*9300)-(entered*8000))+ " WHERE ma_lo_nhap = '" + id + "'";
            statement.executeUpdate(updateQuery);
            statement.close();

            edittList(id, date, entered, sold, ((double)(entered - sold) * 100) / entered, (sold * 9300)-(entered * 8000));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Không thể cập nhật dữ liệu trong MySQL!");
        }
    }
	
	public void deleteFromDatabase(String id) {
        try {
            Statement statement = connection.createStatement();
            String deleteQuery = "DELETE FROM dai_ly_cap_2 WHERE ma_lo_nhap = '" + id + "'";
            statement.executeUpdate(deleteQuery);
            statement.close();

            delete();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Không thể xóa dữ liệu khỏi MYSQL!");
        }
    }
	
	public void load() {
		try {
			vTitle.clear();
			vData.clear();
			int num_column = colums.length;
			
			for(int i = 0; i < num_column; i++) {
				vTitle.add(colums[i]);
			}
			for(int i = 0; i < Data.length; i++) {
				Vector row = new Vector(num_column);
				for(int j = 0; j < num_column; j++) {
					row.add(Data[i][j]);
				}
				vData.add(row);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertList(String id, String date, int entered, int sold, double left, int revenue) {
		try {
			Vector row  = new Vector(); 
			row.add(id);
			row.add(date);
			row.add(entered+"");
			row.add(sold+"");
			row.add(left+"");
			row.add(revenue+"");
			
			vData.add(row);
			model.fireTableDataChanged();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void edittList(String id, String date, int entered, int sold, double left, int revenue) {
		try {
			
			Enumeration e = vData.elements();
			int i=0;
			
			while(e.hasMoreElements()) {
				Vector st = (Vector)e.nextElement();
				if(st.elementAt(0).equals(id)) {
					vData.remove(i); 
					break;
				}
				i++;
			}
			
			Vector row = new Vector();
			row.add(id);
			row.add(date);
			row.add(entered+"");
			row.add(sold+"");
			row.add(left+"");
			row.add(revenue+"");
	
			vData.add(i, row);	
			model.fireTableDataChanged();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete() {
		try {
	        if (vData.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Không có dữ liệu để thực hiện chức năng xóa!", "Error", JOptionPane.ERROR_MESSAGE);
	        } else if (selectedrow >= 0 && selectedrow < vData.size()) {
	            vData.remove(selectedrow);
	            model.fireTableDataChanged();
	        } else {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa!", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void performSearch() {
	    SearchForm searchForm = new SearchForm(null);

	    searchForm.setSearchActionListener(e -> {
	        String searchText = searchForm.getSearchText();

	        if (searchText.isEmpty()) {
	        	JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm!", "Error", JOptionPane.ERROR_MESSAGE);
	        } else {
	            try {
	                String date_compare = searchText;
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	                LocalDate.parse(date_compare, formatter);

	                Vector<Vector<String>> filteredData = new Vector<>();
	                Enumeration<Vector<String>> enumeration = vData.elements();
	                boolean foundMatch = false;

	                while (enumeration.hasMoreElements()) {
	                    Vector<String> row = enumeration.nextElement();
	                    String date = row.get(1).toLowerCase();

	                    if (date.contains(searchText)) {
	                        foundMatch = true;
	                        filteredData.add(row);
	                    }
	                }

	                if (foundMatch) {
	                    new SearchResult2(filteredData, vTitle);
	                    searchForm.dispose();
	                } else {
	                	JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả tương thích!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
	                }
	            } catch (DateTimeParseException ex) {
	            	JOptionPane.showMessageDialog(this, "Ngày sai định dạng: yyyy-mm-dd!", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    });
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		selectedrow=tb.getSelectedRow();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Delete")) {
			int selectedRow = tb.getSelectedRow();
			String id = (String) tb.getValueAt(selectedRow, 0);
			deleteFromDatabase(id);
		}
		
		if(e.getActionCommand().equals("Insert")) {
			new Form("Insert Form",this,"","","","");
		}
		
		if(e.getActionCommand().equals("Edit")) {
			Vector st = (Vector)vData.elementAt(selectedrow);
			
			new Form("Edit Form", this, (String)st.elementAt(0), (String)st.elementAt(1),(String)st.elementAt(2),(String)st.elementAt(3));
		}
		
		if(e.getActionCommand().equals("Search")) {
		    performSearch();
		}
	}
	
}


