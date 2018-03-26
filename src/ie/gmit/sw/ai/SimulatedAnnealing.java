package ie.gmit.sw.ai;

public class SimulatedAnnealing {
	
	public static double probability(int fitness, int nextFitness, double distance) {	
		return (nextFitness > fitness) ? 1.0 : Math.log10((fitness - nextFitness) / 4899000); 
	}// return probability
}
