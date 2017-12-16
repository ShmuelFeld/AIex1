/**
 * the interface for the algorithms
 */
public interface IAlgorithm {
    String findPath(Node root, Node goal, Matrix matrix);
    String toPath(Node goal, Node root);
}
