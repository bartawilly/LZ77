import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class lz77  {
   
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lz77 window = new lz77();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public lz77() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Snap ITC", Font.PLAIN, 16));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(70, 44, 294, 51);
		frame.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("COMPRESS");
		btnNewButton.setFont(new Font("Snap ITC", Font.BOLD, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String s=new String();	
					Scanner reader;
					try {
						reader = new Scanner(new File("Original.txt"));
					
					while(reader.hasNext())
					{
						s=reader.nextLine();
					}
					reader.close();
					
					
					
					
					
					
					FileWriter writef;
					try {
						writef = new FileWriter ("comp.txt");
						BufferedWriter w=new BufferedWriter (writef);
						w.write(",");
					
					
					for(int i=0;i<s.length();i++){
						ArrayList pos=new ArrayList<int[]> (); 
						ArrayList st=new ArrayList<String> (); 
						ArrayList length=new ArrayList<int[]>();
					for(int j=i-1;j>=0;j--){
						
						if(s.charAt(i)==s.charAt(j)){
							pos.add(j);
							st.add(s.substring(j, i));
							
						}
						
					}
				//	System.out.println(st.toString());
					for(int q=0;q<st.size();q++){
						String z=new String ((String)st.get(q));
						int kolo=i;
						length.add(0);
						for(int u=0;u<z.length()&&u<s.length()-kolo+1;u++){
							if(s.charAt(kolo)==z.charAt(u)){
								int o = length.size()-1;
								int g = ((int)length.get(o))+1;
								
								length.set(o,g);
							}
							else
							{
								break;
							}
							kolo++;
							
						}
						
						
					}
					int akbr=0;
					if (length.size()>0)
					{
						for(int r=0;r<length.size();r++){
							if((int)length.get(akbr)<(int)length.get(r))
							{
								akbr=r;
							}
								
						}	
					}
				//	System.out.println(length.toString());
					//System.out.println(pos.toString());
					//System.out.println(akbr+"");
				//	System.out.println(" ");
						if (length.size()>0)
						{
							if(i+(int)length.get(akbr)<s.length())
							{
								int m=(i-(int)pos.get(akbr));
								w.write(String.valueOf(m));
								w.flush();
								w.append(',');
								w.flush();
								m=(int)length.get(akbr);
								w.write(String.valueOf(m));
								w.flush();
								w.append(',');
								w.flush();
								w.write(s.charAt(i+(int)length.get(akbr)));
								w.flush();
								w.append('|');
								w.flush();
								System.out.println((i-(int)pos.get(akbr))+""+","+((int)length.get(akbr))+""+","+s.charAt(i+(int)length.get(akbr))+"");
							
							}
							else
							{
								int m=(i-(int)pos.get(akbr));
								w.write(String.valueOf(m));
								w.flush();
								w.append(',');
								w.flush();
								m=(int)length.get(akbr);
								w.write(String.valueOf(m));
								w.flush();
								w.append(',');
								w.flush();
								w.write("");
								w.flush();
								w.append('|');
								w.flush();
								System.out.println((i-(int)pos.get(akbr))+""+","+((int)length.get(akbr))+""+","+"");
								
							}
							i=i+(int)length.get(akbr);
						}
						else
						{
							int m=(0);
							w.write(String.valueOf(m));
							w.flush();
							w.append(',');
							w.flush();
							w.write(String.valueOf(m));
							w.flush();
							w.append(',');
							w.flush();
							w.write(s.charAt(i));
							w.flush();
							w.append('|');
							w.flush();
							System.out.println(0+","+0+","+s.charAt(i));
						}
						
					}
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(70, 143, 294, 51);
		frame.getContentPane().add(panel_1);
		
		JButton btnDecompress = new JButton("DECOMPRESS");
		btnDecompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String l=new String();	
				Scanner reader;
				try {
					reader = new Scanner(new File("comp.txt"));
				
				while(reader.hasNext())
				{
					l=reader.nextLine();
				}
				reader.close();
				
				 FileWriter writefile=new FileWriter("decompressedtext.txt");
					BufferedWriter writer=new BufferedWriter(writefile);
				String com=new String();
				for (int i=0;i<l.length()-1;i++)
				{
					if( l.substring(i+1).indexOf(",")>0){
					String p=new String((l.substring(i+1)).substring(0, l.substring(i+1).indexOf(",")));
					i=i+p.length()+1;
				   String len=new String((l.substring(i+1)).substring(0, l.substring(i+1).indexOf(",")));
					i=i+len.length()+1;
					String next=new String((l.substring(i+1)).substring(0, l.substring(i+1).indexOf("|")));
					i=i+next.length();
					if(p.equals("0"))
					{
						com=com+next;
					}
					else
					{
						int v=(int)com.length()-Integer.parseInt(p);
						int y =(int)Integer.parseInt(len);
						com=com+(com.substring(v,v+y))+next;
					}
					}
				} 
				System.out.println(com);
				writer.write(com);
				writer.flush();
				writer.close();
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
		});
		btnDecompress.setFont(new Font("Snap ITC", Font.BOLD, 17));
		panel_1.add(btnDecompress);
	}
}
