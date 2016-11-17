package guiProject;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.rtf.*;
import javax.swing.text.StyledEditorKit.*;
import java.awt.event.*;
import java.awt.print.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Word extends JFrame{
	//********************* Global vareabls **********************
		private Container container;
		private JTextPane text;
		private JMenuBar menu;
		private FlowLayout flow=new FlowLayout(FlowLayout.LEFT,0,0);
		private JPanel panel=new JPanel(new BorderLayout());
		private RTFEditorKit rtfKit=new RTFEditorKit();
		public FindReplaceDialog rplace_frame;
		
		private String output="";
		private String output_stream="";
		private String font_name_i="Times New Roman";
		private int font_style=0;
		private int font_size_i=12;
		private int end_of_file;
		private final int CONST_BOLD=Font.BOLD;
		private final int CONST_ITALIC=Font.ITALIC;
		private final int CONST_NULL=0;
		private boolean flage_bold=false;
		private boolean flage_italic=false;
		private boolean flage_under_line=false;
		private boolean opened=false;
		private boolean new_file=false;
		private boolean saved_once=false;
		private File Opened_file;
		private File Saved_once;
		
	//******************* File & Edit & Format Menu vareabls ********************
		private JMenu file, edit, format, color, fonts;
		private JMenuItem New, open, close, save, save_as, print, exit,
						copy, cut, paste, replace, select_all;
		private ButtonGroup font_group;
		private ButtonGroup color_group;
		private String color_name[]={"Black","Green","Blou","Red","Yellow"};
		private Color color_value[]={Color.black, Color.green, Color.blue,
									Color.red, Color.yellow};
		private JRadioButtonMenuItem color_item[], font_item[];
		private JCheckBoxMenuItem bold, italic, under_line;
		
	//******************** Tool Bar vareabls ********************
		private JToolBar tool=new JToolBar();
		private ImageIcon new_icon= new ImageIcon("New.gif");
		private JButton new_button= new JButton(new_icon);
		private ImageIcon open_icon= new ImageIcon("Open.gif");
		private JButton open_button= new JButton(open_icon);
		private ImageIcon save_icon= new ImageIcon("Save.gif");
		private JButton save_button= new JButton(save_icon);
		private ImageIcon print_icon= new ImageIcon("Print.gif");
		private JButton print_button= new JButton(print_icon);
		private ImageIcon cut_icon= new ImageIcon("Cut.gif");
		private JButton cut_button= new JButton(cut_icon);
		private ImageIcon copy_icon= new ImageIcon("Copy.gif");
		private JButton copy_button= new JButton(copy_icon);
		private ImageIcon paste_icon= new ImageIcon("Paste.gif");
		private JButton paste_button= new JButton(paste_icon);
		
	//******************** Font Bar vareabls ********************
		private JToolBar tool_font=new JToolBar();
		private ImageIcon bold_icon= new ImageIcon("Bld.gif");
		private JToggleButton bold_button=new JToggleButton(new StyledEditorKit.BoldAction());
		private ImageIcon italic_icon= new ImageIcon("Itl.gif");
		private JToggleButton italic_button=new JToggleButton(new StyledEditorKit.ItalicAction());
		private ImageIcon under_line_icon= new ImageIcon("Undrln.gif");
		private JToggleButton under_line_button=new JToggleButton(new StyledEditorKit.UnderlineAction());
		private String font_names[]={"Monospaced","Times New Roman","Courier","Tahoma","MS Serif",
				"Andalus","Monotype Koufi","Simplified Arabic"};
		private String font_sizes[]={"10","12","14","16","18","20","22","24","26",
				"28","32","36","40","46","52","60","72"};
		private JComboBox font_name=new JComboBox(font_names);
		private JComboBox font_size=new JComboBox(font_sizes);
		
	//********************* CONSTRACTOR SECTION *********************
		public Word()
		{
			super("Word Processing");
			
	//******************** LOOK AND FEEL SECTION ********************
			try
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null,"Cann't set look and feel",
					"Error",JOptionPane.ERROR_MESSAGE);
			}
	//********************** FILE MENU SECTION **********************
			ItemHandler itemHandler =new ItemHandler();
		//==================== New Item =======================
			New= new JMenuItem("New...",new_icon);
			New.setMnemonic('N');
			New.addActionListener(itemHandler);
		//==================== Open Item ======================
			open= new JMenuItem("Open...",open_icon);
			open.setMnemonic('O');
			open.addActionListener(itemHandler);
		//===================== Close Item ====================
			close= new JMenuItem("Close");
			close.setMnemonic('C');
			close.addActionListener(itemHandler);
		//===================== Save Item =====================
			save= new JMenuItem("Save",save_icon);
			save.setMnemonic('S');
			save.addActionListener(itemHandler);
		//===================== Save As Item ==================
			save_as= new JMenuItem("Save As...",save_icon);
			save_as.setMnemonic('A');
			save_as.addActionListener(itemHandler);
		//===================== Print Item ====================
			print= new JMenuItem("Print...",print_icon);
			print.setMnemonic('P');
			print.addActionListener(itemHandler);
		//===================== Exit Item =====================
			exit= new JMenuItem("Exit");
			exit.setMnemonic('x');
			exit.addActionListener(itemHandler);
		//============= Add Item To The File Menu ===================
			file= new JMenu("File");
			file.setMnemonic('F');
			file.add(New);
			file.add(open);
			file.add(close);
			file.addSeparator();
			file.add(save);
			file.add(save_as);
			file.addSeparator();
			file.add(print);
			file.addSeparator();
			file.add(exit);
			
	//********************* EDIT MENU SECTION ***********************
		//===================== Cut Item ======================
			cut= new JMenuItem("Cut");
			cut.setMnemonic('t');
			cut.addActionListener(itemHandler);
		//===================== Copy Item =====================
			copy= new JMenuItem("Copy");
			copy.setMnemonic('C');
			copy.addActionListener(itemHandler);
		//===================== Replace Item ==================
			replace= new JMenuItem("Replace");
			replace.setMnemonic('e');
			replace.addActionListener(itemHandler);
		//===================== Paste Item ====================
			paste= new JMenuItem("Paste");
			paste.setMnemonic('P');
			paste.addActionListener(itemHandler);
		//===================== Select All Item ===============
			select_all= new JMenuItem("Select All");
			select_all.setMnemonic('l');
			select_all.addActionListener(itemHandler);
		//================ Add Items To The Edit Menu ===============
			edit= new JMenu("Edit");
			edit.setMnemonic('E');
			edit.add(cut);
			edit.add(copy);
			edit.add(replace);
			edit.add(paste);
			edit.addSeparator();
			edit.add(select_all);
			
	//********************* FORMAT MENU SECTION *********************
		//===================== Font Sub Menu =======================
		//===================== Fonts Item ===================
			fonts= new JMenu("Fonts");
			fonts.setMnemonic('t');
			font_group=new ButtonGroup();
			font_item=new JRadioButtonMenuItem[font_names.length];
			for(int k=0;k<font_names.length;k++)
			{
				font_item[k]=new JRadioButtonMenuItem(font_names[k]);
				font_group.add(font_item[k]);
				fonts.add(font_item[k]);
				font_item[k].addActionListener(itemHandler);
			}
			fonts.addSeparator();
		//===================== Bold Item =====================
			bold= new JCheckBoxMenuItem("Bold");
			bold.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent event)
				{
					if(bold.isSelected())
					{
						BOLD();
					}
				}
			});
		//===================== Italic Item ===================
			italic= new JCheckBoxMenuItem("Italic");
			italic.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent event)
				{
					if(italic.isSelected())
					{
						ITALIC();
					}
				}
			});
		//===================== Under Line Item ===============
			under_line=new JCheckBoxMenuItem("Under Line");
		//============== Add Items To Font Sub Menu ===========
			fonts.add(bold);
			fonts.add(italic);
			fonts.add(under_line);
		//===================== Color Sub Menu Item =================
			color= new JMenu("Color");
			color.setMnemonic('r');
			color_group=new ButtonGroup();
			color_item=new JRadioButtonMenuItem[color_name.length];
			for(int i=0;i<color_name.length;i++)
			{
				color_item[i]=new JRadioButtonMenuItem(color_name[i]);
				color.add(color_item[i]);
				color_group.add(color_item[i]);
				color_item[i].addActionListener(itemHandler);
			}
			color_item[0].setSelected(true);
		//===== Add Fonts & Color Sub Menu To Format Menu =====
			format= new JMenu("Format");
			format.setMnemonic('o');
			format.add(color);
			format.addSeparator();
			format.add(fonts);
		//========= Add File & Edit & Format Menu To Menu Bar =======
			menu=new JMenuBar();
			setJMenuBar(menu);
			menu.add(file);
			menu.add(edit);
			menu.add(format);
			
	//*********************** TOOL BAR SECTION **********************
		//===================== Tool Bar Buttons ====================
		//===================== New Button ====================
			new_button.setToolTipText("New File");
			new_button.addActionListener(itemHandler);
		//===================== Open Button ===================
			open_button.setToolTipText("Open File");
			open_button.addActionListener(itemHandler);
		//===================== Save Button ===================
			save_button.setToolTipText("Save File");
			save_button.addActionListener(itemHandler);
		//===================== Print Button ==================
			print_button.setToolTipText("Print File");
			print_button.addActionListener(itemHandler);
		//===================== Cut Button ====================
			cut_button.setToolTipText("Cut Selection Text");
			cut_button.addActionListener(itemHandler);
		//===================== Copy Button ===================
			copy_button.setToolTipText("Copy Selection Text");
			copy_button.addActionListener(itemHandler);
		//===================== Paste Button ==================
			paste_button.setToolTipText("Paste Selected Text");
			paste_button.addActionListener(itemHandler);
		//===================== Add Buttons To Tool Bar =============
			tool.setLayout(flow);
			tool.add(new_button);
			tool.add(open_button);
			tool.add(save_button);
			tool.addSeparator();
			tool.add(print_button);
			tool.addSeparator();
			tool.add(cut_button);
			tool.add(copy_button);
			tool.add(paste_button);
			tool.setVisible(true);
			tool.setEnabled(false);
	//*********************** FONT BAR SECTION **********************
		//===================== Font Size ComboBox ============
			font_size.setToolTipText("Font Size");
			font_size.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent event)
				{
					int index=font_size.getSelectedIndex();
					font_size_i=Integer.parseInt(font_sizes[index]);
					MutableAttributeSet attr=new SimpleAttributeSet();
					StyleConstants.setFontSize(attr,font_size_i);
					text.setCharacterAttributes(attr,false);
					//text.setFont(new Font(font_name_i,font_style,font_size_i));
					text.requestFocus();
				}
			});
		//===================== Font Name ComboBox ============
			font_name.setToolTipText("Font Name");
			font_name.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent event)
				{
					int index=font_name.getSelectedIndex();
					font_name_i=font_names[index];
					font_item[index].setSelected(true);
					MutableAttributeSet attr=new SimpleAttributeSet();
					StyleConstants.setFontFamily(attr,font_name_i);
					text.setCharacterAttributes(attr,false);
					//text.setFont(new Font(font_name_i,font_style,font_size_i));
					text.requestFocus();
				}
			});
		//===================== Bold Button ===================
			bold_button.setText("");
			bold_button.setIcon(bold_icon);
			bold_button.setToolTipText("Bold Style");
			bold_button.addActionListener(itemHandler);
		//===================== Italic Button =================
			italic_button.setText("");
			italic_button.setIcon(italic_icon);
			italic_button.setToolTipText("Italic Style");
			italic_button.addActionListener(itemHandler);
		//===================== Under Line Button =============
			under_line_button.setText("");
			under_line_button.setIcon(under_line_icon);
			under_line_button.setToolTipText("UnderLine Style");
			under_line_button.addActionListener(itemHandler);
		//===================== Add Buttons To Font Bar =======
			tool_font.setLayout(flow);
			tool_font.add(font_name);
			tool_font.addSeparator();
			tool_font.add(font_size);
			tool_font.addSeparator();
			tool_font.add(bold_button);
			tool_font.add(italic_button);
			tool_font.add(under_line_button);
			tool_font.addSeparator();
			tool_font.setVisible(true);
			tool_font.setEnabled(false);
		//===================== Add ToolBar & FontBar To Panel ======
			panel.setBorder(BorderFactory.createEtchedBorder());
			panel.add(tool,BorderLayout.NORTH);
			panel.add(tool_font,BorderLayout.SOUTH);
			
	//***************** Add All Componant To the Container **********
			text=new JTextPane();
			text.setEditorKit(rtfKit);
			text.setBorder(BorderFactory.createEtchedBorder());
		//==================== ADD CARET LISTENER TO THE TEXTPANE =======
			text.addCaretListener(new CaretListener()
			{
				public void caretUpdate(CaretEvent event)
				{
					StyledDocument doc=text.getStyledDocument();
					AttributeSet a=doc.getCharacterElement(event.getDot()-1).getAttributes();
					flage_bold=StyleConstants.isBold(a);
					if(flage_bold != bold_button.isSelected())
						bold_button.setSelected(flage_bold);
					flage_italic=StyleConstants.isItalic(a);
					if(flage_italic != bold_button.isSelected())
						italic_button.setSelected(flage_italic);
					flage_under_line=StyleConstants.isUnderline(a);
					if(flage_under_line != under_line_button.isSelected())
						under_line_button.setSelected(flage_under_line);
					font_name_i=StyleConstants.getFontFamily(a);
					if(font_name_i!=font_name.getSelectedItem().toString())
						font_name.setSelectedItem(font_name_i);
					font_size_i=StyleConstants.getFontSize(a);
					if(font_size_i!=Integer.parseInt(font_size.getSelectedItem().toString()))
						font_size.setSelectedItem(String.valueOf(font_size_i));
				}
			});
			SetDisable_JTextPane();
			container=getContentPane();
			container.setLayout(new BorderLayout());
			container.add(panel,BorderLayout.NORTH);
			container.add(text,BorderLayout.CENTER);
			container.add(new JScrollPane(text),BorderLayout.CENTER);
			setSize(500,400);
			
	//********************* Default Close From Windows **************
			addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					EXIT_PROGRAM();
				}
			});
		} // END THE CONSTRACTOR
		
	//********************* MAIN FUNCTION SECTION *******************
		public static void main(String asgv[])
		{
			Word w=new Word();
			JSplash splash=new JSplash(w);
			splash.setVisible(true);
			try
			{
				Thread.sleep(5000);
			}
			catch(Exception ex)
			{}
			w.setVisible(true);
			splash.setVisible(false);
			w.setLocation(150,80);
			w.setResizable(false);
			w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	//********************* NEW_FILE Function ***********************
		public void NEW_FILE()
		{
			if(opened==true||new_file==true)
			{
				if(!text.equals(""))
				{
					int answer=JOptionPane.showConfirmDialog(null,
						"Do you want to save this file....?");
					if(answer==JOptionPane.OK_OPTION)
					{
						SAVE_FILE();
					}
					else
						if(answer==JOptionPane.CANCEL_OPTION)
							return;
				}
			}
			SetEnable_JTextPane();
			text.selectAll();
			int start=text.getSelectionStart();
			int end=text.getSelectionEnd();
			try 
			{
				text.getDocument().remove(start,end);
			}
			catch(Exception ex)
			{}
			opened=false;
			new_file=true;
			saved_once=false;
		}
	//********************* OPEN_FILE Function **********************
		public void OPEN_FILE()
		{
			JFileChooser file_chooser=new JFileChooser(".");
			file_chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int answer=file_chooser.showOpenDialog(null);
			if(answer==JFileChooser.CANCEL_OPTION)
				return;
			else if(answer==JFileChooser.OPEN_DIALOG)
			{
				Opened_file=file_chooser.getSelectedFile();
				if(Opened_file!=null||Opened_file.getName()!="")
				{
					try
					{
						BufferedReader stream=new BufferedReader(new FileReader (Opened_file));
						String line;
						output="";
						while((line=stream.readLine())!=null)
						{
							output+=line;
							output+="\n";
						}
						text.setText(output);
					}	
					catch(IOException ex)
				    {
				    	JOptionPane.showConfirmDialog(null,
				    	"error reading from the file",
				    	"error reading",
				    	JOptionPane.ERROR_MESSAGE);
				    }
				}
			opened=true;
			new_file=false;
			}
		}
	//********************* SAVE_FILE Function ***********************
		public void SAVE_FILE()
		{
			JFileChooser file_chooser=new JFileChooser();
			file_chooser.setCurrentDirectory(new File("."));
			if(opened==true)
			{
				try
				{
					FileOutputStream stream=new FileOutputStream(Opened_file);
	              	rtfKit.write(stream,text.getDocument(),0,
	              					text.getDocument().getLength());
	              	stream.close();	
	            }
	            catch(Exception ex)
	            {}
			}
			else if(saved_once==true)
			{
				try
				{
					FileOutputStream stream=new FileOutputStream(Saved_once);
	              	rtfKit.write(stream,text.getDocument(),0,
	              					text.getDocument().getLength());
	              	stream.close();
	            }
	            catch(Exception ex)
	            {}
			}
			else
			{
				if(file_chooser.showSaveDialog(Word.this)==0)
				{
					try
					{
						String file_name=file_chooser.getSelectedFile().toString()
												+".wrd";
						File file_out=new File(file_name);
						FileOutputStream stream=new FileOutputStream(file_out);
	    	          	rtfKit.write(stream,text.getDocument(),0,
	    	          					text.getDocument().getLength());
	    	          	Saved_once=file_out;
	    	          	saved_once=true;
	   		           	stream.close();
	        	    } 
	      		    catch (Exception ex)
	      		    {
	     	         	ex.printStackTrace();
	        	    }
	            	file_chooser.rescanCurrentDirectory();
	         	}
	        }
		}
	//********************* SAVE_AS_FILE Function ********************
		public void SAVE_AS_FILE()
		{
			JFileChooser file_chooser=new JFileChooser();
			file_chooser.setCurrentDirectory(new File("."));
			if(file_chooser.showSaveDialog(Word.this)==0)
			{
				try
				{
					String file_name=file_chooser.getSelectedFile().toString()
											+".wrd";
					File file_out=new File(file_name);
					FileOutputStream stream=new FileOutputStream(file_out);
	   	          	rtfKit.write(stream,text.getDocument(),0,
	   	          					text.getDocument().getLength());
	   	          	Saved_once=file_out;
	   	          	saved_once=true;
		           	stream.close();
	       	    } 
	   		    catch (Exception ex)
	   		    {
	  	         	ex.printStackTrace();
	      	    }
	           	file_chooser.rescanCurrentDirectory();
	       	}
	    }
	//********************* EXIT_PROGRAM Function ********************
		public void CLOSE_FILE()
		{
			if(opened==true||new_file==true)
			{
				int answer=JOptionPane.showConfirmDialog(null,"Would you like to Save this file...?"
					,"Closing File",JOptionPane.YES_NO_CANCEL_OPTION);
				if(answer==JOptionPane.OK_OPTION)
				{
					SAVE_FILE();
					text.selectAll();
					try
					{
						text.getDocument().remove(text.getSelectionStart(),
											text.getSelectionEnd());
					}
					catch(Exception ex)
					{}
					SetDisable_JTextPane();
				}
				else if(answer==JOptionPane.CANCEL_OPTION)
					return;
				else
				{
					text.selectAll();
					try
					{
						text.getDocument().remove(text.getSelectionStart(),
											text.getSelectionEnd());
					}
					catch(Exception ex)
					{}
					SetDisable_JTextPane();
				}
			}
		}
	//********************* EXIT_PROGRAM Function ********************
		public void EXIT_PROGRAM()
		{
			if(!text.equals(""))
			{
				int answer=JOptionPane.showConfirmDialog(null,
						"Do you want to save this file....?");
				if(answer==JOptionPane.OK_OPTION)
				{
					SAVE_FILE();
					System.exit(0);
				}
				else if(answer==JOptionPane.NO_OPTION)
					System.exit(0);
				else
					return;
			}
			else
				System.exit(0);
		}
	//********************* BOLD Function ***************************
		public void BOLD()
		{
			flage_bold=!flage_bold;
			if(flage_bold&&flage_italic)
				font_style=CONST_BOLD+CONST_ITALIC;
			if(!flage_bold&&!flage_italic)
				font_style=CONST_NULL;
			if(flage_bold&&!flage_italic)
				font_style=CONST_BOLD;
			if(!flage_bold&&flage_italic)
				font_style=CONST_ITALIC;
			text.setFont(new Font(font_name_i,font_style,font_size_i));
			repaint();
		}
	//********************* ITALIC Function *************************
		public void ITALIC()
		{
			flage_italic=!flage_italic;
			if(flage_bold&&flage_italic)
				font_style=CONST_BOLD+CONST_ITALIC;
			if(!flage_bold&&!flage_italic)
				font_style=CONST_NULL;
			if(flage_bold&&!flage_italic)
				font_style=CONST_BOLD;
			if(!flage_bold&&flage_italic)
				font_style=CONST_ITALIC;
			text.setFont(new Font(font_name_i,font_style,font_size_i));
			repaint();
		}
	//********************* SetEnable ****************************
		public void SetEnable_JTextPane()
		{
			text.setBackground(Color.white);
			text.setEditable(true);
			text.requestFocus();
		}
	//********************* SetDisable ****************************
		public void SetDisable_JTextPane()
		{
			text.setBackground(Color.gray);
			text.setEditable(false);
		}
	//********************* HANDLING THE ACTIONLISTENER **************
	private class ItemHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource()==new_button||event.getSource()==New)
				NEW_FILE();
			if(event.getSource()==close)
			{
				CLOSE_FILE();
				new_file=false;
				opened=false;
			}
			if(event.getSource()==open_button||event.getSource()==open)
				OPEN_FILE();
			if(event.getSource()==save_button||event.getSource()==save)
				SAVE_FILE();
			if(event.getSource()==save_as)
				SAVE_AS_FILE();
			if(event.getSource()==exit)
				EXIT_PROGRAM();
			if(event.getSource()==print||event.getSource()==print_button)
			{
				Print p=new Print(text);
				p.peintComponent(text);
			}
			if(event.getSource()==cut||event.getSource()==cut_button)
				text.cut();
			if(event.getSource()==copy||event.getSource()==copy_button)
				text.copy();
			if(event.getSource()==paste||event.getSource()==paste_button)
				text.paste();
			if(event.getSource()==replace)
			{
				rplace_frame=new FindReplaceDialog(Word.this);
				rplace_frame.setVisible(true);
			}
			if(event.getSource()==select_all)
				text.selectAll();
			if(event.getSource()==bold_button)
			{}
			if(event.getSource()==italic_button)
			{}
			if(event.getSource()==under_line_button)
			{}
			for(int k=0;k<font_names.length;k++)
				if(font_item[k].isSelected())
				{
					font_name.setSelectedIndex(k);
					text.setFont(new Font(font_names[k],font_style,
										font_size_i));
					break;
				}
			for(int i=0;i<color_name.length;i++)
				if(color_item[i].isSelected())
				{
					text.setForeground(color_value[i]);
					break;
				}
			text.requestFocus();
			repaint();
		}
	}
	//*************************** PRINT CLASS ************************
		private class Print implements Printable
		{
			private Component component_to_be_print;
			public Print(Component component)
			{
				this.component_to_be_print=component;
			}
			public void print()
			{
				PrinterJob printer_job = PrinterJob.getPrinterJob();
				printer_job.setPrintable(this);
				if(printer_job.printDialog())
				{
					try
					{
						printer_job.print();
					}
					catch(PrinterException pex)
					{
						JOptionPane.showMessageDialog(null,"Error Printing"+pex,
							"Printer Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			public void peintComponent(Component component)
			{
				new Print(component).print();
			}
			public int print(Graphics g,PageFormat page_format,int page_index)
			{
				if(page_index>0)
					return(NO_SUCH_PAGE);
				else
				{
					Graphics2D graphics2d=(Graphics2D)g;
					graphics2d.translate(page_format.getImageableX(),
											page_format.getImageableY());
					disableDoubleBuffering(component_to_be_print);
					component_to_be_print.paint(graphics2d);
					enableDoubleBuffering(component_to_be_print);
					return(PAGE_EXISTS);
				}
			}
			public void disableDoubleBuffering(Component component)
			{
				RepaintManager current_manager=RepaintManager.currentManager(component);
				current_manager.setDoubleBufferingEnabled(false);
			}
			public void enableDoubleBuffering(Component component)
			{
				RepaintManager current_manager=RepaintManager.currentManager(component);
				current_manager.setDoubleBufferingEnabled(true);
			}
		}
	//************************ FIND & REPLACE CLASS *****************
		private class FindReplaceDialog extends JDialog 
		{
			private int index=0;
			private int curOfSet=0;
			private JTextField search_text, replace_text;
			private JButton find_button, find_first_button, replace_button;
			private JLabel find_label, replace_label;
			private JPanel text_panel, button_panel;
			public FindReplaceDialog(JFrame parent)
			{
				super(parent,"Find/Replace",false);
				search_text = new JTextField(16);
				replace_text = new JTextField(16);
				find_button = new JButton("Find");
				find_button.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						FIND();
					}
				});
				find_first_button = new JButton("Find First");
				find_first_button.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						FIND_FIRST();
					}
				});
				replace_button = new JButton("Replace");
				replace_button.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						text.replaceSelection(replace_text.getText());
					}
				});
				find_label=new JLabel("Find What: ");
				replace_label=new JLabel("Replace With: ");
				text_panel = new JPanel();
				text_panel.setLayout(flow);
				text_panel.add(find_label);
				text_panel.add(search_text);
				text_panel.add(replace_label);
				text_panel.add(replace_text);
				text_panel.setBorder(BorderFactory.createEtchedBorder());
				button_panel = new JPanel();
				button_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
				button_panel.add(find_button);
				button_panel.add(find_first_button);
				button_panel.add(replace_button);
				button_panel.setBorder(BorderFactory.createEtchedBorder());
				getContentPane().setLayout(new BorderLayout());
				getContentPane().add(text_panel, BorderLayout.NORTH);
				getContentPane().add(button_panel, BorderLayout.CENTER);
				addWindowListener(new WindowAdapter()
				{
					public void windowClosing(WindowEvent evt)
					{
						dispose();
						setVisible(false);
					}
				});
				setLocation(200,200);
				pack();
				setResizable(false);
			}//end constractor
			public void FIND_FIRST()
			{
				text.selectAll();
				String all_text=text.getSelectedText();
				index=curOfSet=0;
				index=all_text.indexOf(search_text.getText(), curOfSet);
				curOfSet = index + search_text.getText().length();
				if (index > -1) 
					text.select(index,curOfSet);
			}
			public void FIND()
			{
				text.selectAll();
				String all_text=text.getSelectedText();
				index=text.getCaretPosition();
				index = all_text.indexOf(search_text.getText(), curOfSet);
				curOfSet = index + search_text.getText().length();
				if (index > -1)
					text.select(index,curOfSet);
				else
				{
					text.selectAll();
					all_text=text.getSelectedText();
					index=curOfSet=0;
					index = all_text.indexOf(search_text.getText(), curOfSet);
					curOfSet = index + search_text.getText().length();
					if (index > -1)
						text.select(index,curOfSet);
				}
			}
		}
	//****************************************************************
	} //END THE PROGRAM...