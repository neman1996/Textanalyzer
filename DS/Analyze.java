import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Analyze
{
		private LinkedList<String> sentence = new LinkedList<String>();
		private Scanner in = new Scanner(System.in);
		private HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
		private Set wordSet = null;
		private Iterator wordList = null;
		private Map.Entry word = null;
		private int score = 0;
		private int i = 0;
		private String line = null;
		private JFrame frame = new JFrame("Sentimental analyzer");
		private JLabel title = new JLabel("Sentimental Analyzer");
		private JPanel[] panel = new JPanel[6];
		public JTextArea input = new JTextArea("Enter a sentence...",4,20);
		private JButton submit = new JButton("Submit");
		private JButton reset = new JButton("Reset");
		public JLabel status = new JLabel("Status Not Available");
		
		public Analyze()
		{
			
		}
		
		public void buildMAP()
		{
			try
			{
				BufferedReader positive = new BufferedReader(new FileReader("positive.txt"));
				while((line = positive.readLine())!= null)
					wordMap.put(line, 1);
				positive.close();
			
				BufferedReader negative = new BufferedReader(new FileReader("negative.txt"));
				while((line = negative.readLine()) != null)
					wordMap.put(line, -1);
				negative.close();
			}
			catch(Exception e)
			{
				System.out.println("Some error occured !");
			}
			
			wordSet = wordMap.entrySet();
			wordList = wordSet.iterator();
			/*while(wordList.hasNext())
			{
				word = (Map.Entry)wordList.next();
				System.out.println("This word "+word.getKey()+" is "+word.getValue());
			}*/
		}
		
		public void buildGUI()
		{
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800,600);
			frame.setLayout(new GridLayout(6,1,10,10));
			for(int m=0; m<6; ++m)
			{
				panel[m] = new JPanel();
				frame.add(panel[m]);
			}
			
			submit.setActionCommand("Submit");
			submit.addActionListener(new Clicked());
			reset.setActionCommand("Reset");
			reset.addActionListener(new Clicked());
			panel[0].add(title);
			panel[1].add(input);
			panel[2].add(submit);
			panel[3].add(reset);
			panel[4].add(status);
			frame.setVisible(true);
		}
		
		public int start(String s)
		{
			score = 0;
			sentence.clear();
			/*System.out.println("Enter a sentence: ");
			line = in.nextLine();*/
			in = new Scanner(s);
			while(in.hasNext())
			{
				sentence.add(in.next());
			}
			//System.out.println(sentence);
			
			for(i=0; i<sentence.size(); ++i)
			{
				if(wordMap.containsKey(sentence.get(i)))
					score += wordMap.get(sentence.get(i));
			}
			//System.out.println("Your score is: "+score);
			return score;
		}
	
	
	private class Clicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String command = e.getActionCommand();
			if(command.equals("Submit"))
			{
				System.out.println("You're in !!");
				int i = start(input.getText());
				if(i>0)
					status.setText("Positive :) ");
				else if(i<0)
					status.setText("Negative :( ");
				else
					status.setText("Neutral -_-");
			}
			
			if(command.equals("Reset"))
			{
				input.setText(""); 	
			}
		}
	}
	
}