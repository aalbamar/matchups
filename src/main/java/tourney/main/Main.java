/*
 * 
 * Una empresa que organiza torneos de videojuegos online necesita un programa que se encargue del emparejamiento de los mismos para las competiciones.
 * Los jugadores se componen de nombre, apellido, ingenio y rapidez.
 * 
 * Hay 2 tipos diferentes de jugadores y cada uno calcula su nivel de habilidad (ELO) de formas diferentes:
 * - Carry:  rapidez * 2 + ingenio * 0.5
 * - Coach: ingenio * 3 + rapidez * 0.2
 * 
 * Dados 20 equipos de 5 jugadores, cada uno de ellos de tipo y atributos aleatorio, haz un programa que calcule el emparejamiento mas justo entre 
 * los equipos y muestre por consola el resultado.
 * También se necesita que se pueda obtener una lista con los 5 mejores jugadores y otra de los 5 mejores equipos.
 * 
*/
package tourney.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tourney.controller.TourneyManager;

public class Main {
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.print("$ ");
			String input;
			while( (input = br.readLine()) != null ) {
				runOperation(input);
				System.out.print("$ ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void runOperation(String inputOperation) {
		try {
			OperationType operation = parseOperation(inputOperation);
			switch( operation ) {
				case NEW_TOURNEY:
					runNewTourney();
					break;
				case MATCHUP_TEAMS:
					runMatchups();
					break;
				case TOP5_TEAMS:
					runTop5Teams();
					break;
				case TOP5_PLAYERS:
					runTop5Players();
					break;
				case SHOW_TOURNEY:
					runShowTourney();
					break;
				case UNKNOWN:
					System.out.println("Operacion inválida.");
				default:
					printUsage();
					break;
			}
		} catch (Exception e) {
			String error = "Error al ejecutar operacion " + inputOperation;
			logger.error(error, e);
			System.out.println(error);
		}
	}

	private static OperationType parseOperation (String inputOperation) {
		for (Enum<OperationType> operationType : OperationType.values()) {
			if( OperationType.valueOf(operationType.name()).getInputOperation().equalsIgnoreCase(inputOperation) )
				return OperationType.valueOf(operationType.name());
		}
		
		return OperationType.UNKNOWN;
	}

	private static void runNewTourney() {
		TourneyManager.newTourney();
		System.out.println(TourneyManager.getOutput());
	}

	private static void runMatchups() {
		TourneyManager.matchups();
		System.out.println(TourneyManager.getOutput());
	}

	private static void runTop5Teams() {
		TourneyManager.top5Teams();
		System.out.println(TourneyManager.getOutput());
	}

	private static void runTop5Players() {
		TourneyManager.top5Players();
		System.out.println(TourneyManager.getOutput());
	}

	private static void runShowTourney() {
		TourneyManager.showTourney();
		System.out.println(TourneyManager.getOutput());
	}

	private static void printUsage() {
		String usage = "Uso:" + "\n" +
					   "\t" + "[<operacion>]" + "\n" +
					   "\t\t" + "nuevo" + "\n" +
					   "\t\t\t" + "Genera un nuevo torneo." + "\n" +
					   "\t\t" + "emparejar" + "\n" +
					   "\t\t\t" + "Empareja equipos." + "\n" +
					   "\t\t" + "top5e" + "\n" +
					   "\t\t\t" + "Muestra los 5 mejores equipos." + "\n" +
					   "\t\t" + "top5j" + "\n" +
					   "\t\t\t" + "Muestra los 5 mejores jugadores." + "\n" +
					   "\t\t" + "ver" + "\n" +
					   "\t\t\t" + "Visualiza los datos del torneo. Operación por defecto." + "\n";
		
		System.out.println(usage);
	}
}
