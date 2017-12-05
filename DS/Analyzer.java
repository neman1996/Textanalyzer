import java.util.*;
public class Analyzer
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		Analyze me = new Analyze();
		me.buildMAP();
		me.buildGUI();
		/*System.out.println("Enter a sentence");
		String line = in.nextLine();
		me.start(line);*/
	}
}