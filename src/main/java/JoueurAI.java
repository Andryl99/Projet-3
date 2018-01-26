import com.gg.comportement.*;

public class JoueurAI extends Player {
	public JoueurAI() {
		this.play = new ComportementAI();
		this.answer = new ReponseAuto();
		
		
	}
}
