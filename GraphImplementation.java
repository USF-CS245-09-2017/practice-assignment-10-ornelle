import java.util.*;

public class GraphImplementation implements Graph{
	int [][]adjMatrix;
	public GraphImplementation (int size){
		this.adjMatrix = new int[size][size];
	}


	public void addEdge(int v1, int v2){
		adjMatrix[v1][v2] = 1;
	}
	public List<Integer> topologicalSort(){
		List <Integer> sortedList = new ArrayList<Integer> ();
		int temp[]= new int[adjMatrix.length];
		int sum=0;
		int vertices_zero=0;

		/* FIND INCIDENT ARRAY */
		for(int i=0; i<adjMatrix.length; i++){
			for(int j=0; j<adjMatrix.length; j++){
				sum+=adjMatrix[j][i];
			}
			temp[i]=sum;
			sum=0;
		}
		/* SORT */
		for(int i=0; i< temp.length; i++){ //for the function
			for(int j=0; j<temp.length; j++){
				if(temp[j]==0){
					sortedList.add(j); //add index of item to arr
					List<Integer> neigh_B = neighbors_helper(j);
					for(int k=0; k<neigh_B.size(); k++){
						temp[neigh_B.get(k)]--;
					}
					temp[j]=-1; //deals with part of the cycles 
					break;
				}
			}
		}
		return sortedList;
	}
	public int[] neighbors(int vertex){
		List<Integer> a = neighbors_helper(vertex);
		int[] arr = new int[a.size()];
		for (int i = 0; i < a.size(); i++) {
			arr[i] = a.get(i);
		}
		return arr;
	}
	private List<Integer> neighbors_helper(int vertex){
		List<Integer> neigh = new ArrayList<Integer>();
		for(int i=0; i< adjMatrix.length; i++){
			if(adjMatrix[vertex][i]>0)
				neigh.add(i);
		}
		return neigh;
	}
}