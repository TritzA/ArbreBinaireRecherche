public class Interview<ValueType extends Comparable<? super ValueType>> {
    // 2D array of any shape which contains elements sorted from left to right, top to bottom
    private final ValueType[][] matrix;

    public Interview(ValueType[][] matrix) {
        this.matrix = matrix.clone();
    }

    /**
     * TODO Worst case : O ( max(log n, log m) )
     * <p>
     * Verifies if the value is contained within the 2D array
     *
     * @param value value to verify
     * @return if value is in matrix
     */
    public boolean contains(ValueType value) {
        int col = binarySearchColumn(matrix, value);
        if (col == -1) {
            return false;
        } else if (col == -2) {
            return true;
        }

        ValueType valFin = matrix[col][matrix[col].length - 1];
        if (valFin.equals(value)) {
            return true;
        } else if (valFin.compareTo(value) > 0) {//Fin, il sait sur quelle ligne aller
            return binarySearchLine(matrix, value, col);//retourne s'il est sur la ligne
        }


        return false;
    }

    private boolean binarySearchLine(ValueType mat[][], ValueType value, int i) {
        int start = 0;
        int end = mat[i].length - 1;
        while (start <= end) {
            int mid = (int) Math.floor((start + end) / 2);
            if (mat[i][mid].equals(value)) {
                return true;
            } else if (mat[i][mid].compareTo(value) < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

    private int binarySearchColumn(ValueType mat[][], ValueType value) {
        int start = 0;
        int end = mat.length - 1;
        while (start <= end) {
            int mid = (int) Math.floor((start + end) / 2);
            if (mat[mid][0].equals(value)) {
                return -2;//ligne la valeur est en pos [0]
            } else if (mat[mid][0].compareTo(value) < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }


}
