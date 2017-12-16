import java.util.LinkedList;
import java.util.List;

/**
 * this class is the abstract class Algorithm which A* and IDS classes extends her
 */
public abstract class Algorithm implements IAlgorithm {
    /**
     * recover the path from root to goal.
     * @param goal is the goal node
     * @param root is the root node
     * @return a string in the wanted format
     */
    @Override
    public String toPath(Node goal, Node root) {
        if (goal != null) {
            List<Node> linkedList = new LinkedList<Node>();
            linkedList.add(goal);
            goal = goal.getFather();
            while (!goal.equals(root)) {
                linkedList.add(goal);
                goal = goal.getFather();
            }
            linkedList.add(goal);
            StringBuilder sb = new StringBuilder();
            int cost = 0;
            for (Node n : linkedList) {
                cost += n.getCost();
                try {
                    sb.append(n.fromFatherToMe());
                    sb.append("-");
                } catch (Exception e) {
                    //DoNothing
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
            sb = sb.reverse();
            sb.append(" ");
            sb.append(cost);
            return sb.toString();
        }
        return null;
    }
}
