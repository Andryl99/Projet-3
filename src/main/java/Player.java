import com.gg.comportement.*;

public abstract class Player {
	
	protected Play play = new ComportementHumain();
	protected Answer answer = new ReponseManuelle();
	
	public Player() {}
	
	public Player(Play play, Answer answer) {
	this.play = play;
	this.answer = answer;
	}
	
	public String  joueUnCoup(String reponse, String derniereProposition, int nbCases)	{
		return play.JouerUnCoup(reponse, derniereProposition, nbCases);
	}
	
	public String donneReponse(String proposition, String solution, int nbCases) {
		return answer.DonnerReponse(proposition, solution, nbCases);
	}
	
	public String choisitSolution(int nbCases)	{
		return play.ChoisirSolution(nbCases);
	}
}
