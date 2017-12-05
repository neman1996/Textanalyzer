import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class analyze
{
	public static JLabel status;
	public static void main(String args[])
	{
	        LinkedList<String> sentence = new LinkedList<String>();
	        Scanner in = new Scanner(System.in);
	        HashMap hashmap = new HashMap();
	        JPanel panel[][] = new JPanel[6][3];
        try
        {
        	BufferedReader bufferedreader = new BufferedReader(new FileReader("positive.txt"));
		BufferedReader bufferedreader1 = new BufferedReader(new FileReader("negative.txt"));
		for(String s = null; (s = bufferedreader.readLine()) != null;)
               		hashmap.put(s, Integer.valueOf(1));

            bufferedreader.close();
            String s1;
            while((s1 = bufferedreader1.readLine()) != null) 
                hashmap.put(s1, Integer.valueOf(-1));
            bufferedreader1.close();
        }
        catch(Exception exception)
        {
            System.out.println("Exception !");
        }
        Set set = hashmap.entrySet();
        Iterator iterator = set.iterator();
        JFrame frame = new JFrame("Sentimental Analyzer");
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(6, 3, 50, 20));
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                panel[i][j] = new JPanel();
                frame.add(panel[i][j]);
            }

        }

        JLabel jlabel = new JLabel("Sentimental Analyzer", 0);
        jlabel.setFont(new Font("Courier New", 2, 30));
        status = new JLabel("", 0);
        JTextArea jtextarea = new JTextArea("Enter a sentence", 5, 20);
        status.setSize(350, 100);
        frame.setDefaultCloseOperation(3);
        JButton jbutton = new JButton("Analyze");
        jbutton.addActionListener(new CustomActionListener());
        panel[0][1].add(jlabel);
        panel[1][1].add(jtextarea);
        panel[2][1].add(jbutton);
        frame.setVisible(true);
    }

	static class CustomActionListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			analyze.status.setText("Ok Button Clicked.");
		}
	}
}
