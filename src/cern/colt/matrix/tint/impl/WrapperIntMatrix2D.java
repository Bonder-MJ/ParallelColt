/*
Copyright (C) 1999 CERN - European Organization for Nuclear Research.
Permission to use, copy, modify, distribute and sell this software and its documentation for any purpose 
is hereby granted without fee, provided that the above copyright notice appear in all copies and 
that both that copyright notice and this permission notice appear in supporting documentation. 
CERN makes no representations about the suitability of this software for any purpose. 
It is provided "as is" without expressed or implied warranty.
 */
package cern.colt.matrix.tint.impl;

import cern.colt.matrix.tint.IntMatrix1D;
import cern.colt.matrix.tint.IntMatrix2D;

/**
 * 2-d matrix holding <tt>int</tt> elements; either a view wrapping another
 * matrix or a matrix whose views are wrappers.
 * 
 * @author wolfgang.hoschek@cern.ch
 * @version 1.0, 04/14/2000
 * 
 * @author Piotr Wendykier (piotr.wendykier@gmail.com)
 * @version 1.1, 08/22/2007
 */
public class WrapperIntMatrix2D extends IntMatrix2D {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /*
     * The elements of the matrix.
     */
    protected IntMatrix2D content;

    public WrapperIntMatrix2D(IntMatrix2D newContent) {
        if (newContent != null)
            setUp(newContent.rows(), newContent.columns());
        this.content = newContent;
    }

    @Override
    public Object elements() {
        return content.elements();
    }

    /**
     * Returns the matrix cell value at coordinate <tt>[row,column]</tt>.
     * 
     * <p>
     * Provided with invalid parameters this method may return invalid objects
     * without throwing any exception. <b>You should only use this method when
     * you are absolutely sure that the coordinate is within bounds.</b>
     * Precondition (unchecked):
     * <tt>0 &lt;= column &lt; columns() && 0 &lt;= row &lt; rows()</tt>.
     * 
     * @param row
     *            the index of the row-coordinate.
     * @param column
     *            the index of the column-coordinate.
     * @return the value at the specified coordinate.
     */
    @Override
    public int getQuick(int row, int column) {
        return content.getQuick(row, column);
    }

    /**
     * Construct and returns a new empty matrix <i>of the same dynamic type</i>
     * as the receiver, having the specified number of rows and columns. For
     * example, if the receiver is an instance of type <tt>DenseIntMatrix2D</tt>
     * the new matrix must also be of type <tt>DenseIntMatrix2D</tt>, if the
     * receiver is an instance of type <tt>SparseIntMatrix2D</tt> the new matrix
     * must also be of type <tt>SparseIntMatrix2D</tt>, etc. In general, the new
     * matrix should have internal parametrization as similar as possible.
     * 
     * @param rows
     *            the number of rows the matrix shall have.
     * @param columns
     *            the number of columns the matrix shall have.
     * @return a new empty matrix of the same dynamic type.
     */
    @Override
    public IntMatrix2D like(int rows, int columns) {
        return content.like(rows, columns);
    }

    /**
     * Construct and returns a new 1-d matrix <i>of the corresponding dynamic
     * type</i>, entirelly independent of the receiver. For example, if the
     * receiver is an instance of type <tt>DenseIntMatrix2D</tt> the new matrix
     * must be of type <tt>DenseIntMatrix1D</tt>, if the receiver is an instance
     * of type <tt>SparseIntMatrix2D</tt> the new matrix must be of type
     * <tt>SparseIntMatrix1D</tt>, etc.
     * 
     * @param size
     *            the number of cells the matrix shall have.
     * @return a new matrix of the corresponding dynamic type.
     */
    @Override
    public IntMatrix1D like1D(int size) {
        return content.like1D(size);
    }

    /**
     * Sets the matrix cell at coordinate <tt>[row,column]</tt> to the specified
     * value.
     * 
     * <p>
     * Provided with invalid parameters this method may access illegal indexes
     * without throwing any exception. <b>You should only use this method when
     * you are absolutely sure that the coordinate is within bounds.</b>
     * Precondition (unchecked):
     * <tt>0 &lt;= column &lt; columns() && 0 &lt;= row &lt; rows()</tt>.
     * 
     * @param row
     *            the index of the row-coordinate.
     * @param column
     *            the index of the column-coordinate.
     * @param value
     *            the value to be filled into the specified cell.
     */
    @Override
    public void setQuick(int row, int column, int value) {
        content.setQuick(row, column, value);
    }

    /**
     * Returns a vector obtained by stacking the columns of the matrix on top of
     * one another.
     * 
     * @return a vector obtained by stacking the columns of the matrix on top of
     *         one another.
     */
    @Override
    public IntMatrix1D vectorize() {
        return content.vectorize();
    }

    /**
     * Constructs and returns a new <i>slice view</i> representing the rows of
     * the given column. The returned view is backed by this matrix, so changes
     * in the returned view are reflected in this matrix, and vice-versa. To
     * obtain a slice view on subranges, construct a sub-ranging view (
     * <tt>viewPart(...)</tt>), then apply this method to the sub-range view.
     * <p>
     * <b>Example:</b>
     * <table border="0">
     * <tr nowrap>
     * <td valign="top">2 x 3 matrix: <br>
     * 1, 2, 3<br>
     * 4, 5, 6</td>
     * <td>viewColumn(0) ==></td>
     * <td valign="top">Matrix1D of size 2:<br>
     * 1, 4</td>
     * </tr>
     * </table>
     * 
     * @param column
     *            the column to fix.
     * @return a new slice view.
     * @throws IndexOutOfBoundsException
     *             if <tt>column < 0 || column >= columns()</tt>.
     * @see #viewRow(int)
     */
    @Override
    public IntMatrix1D viewColumn(int column) {
        return viewDice().viewRow(column);
    }

    /**
     * Constructs and returns a new <i>flip view</i> along the column axis. What
     * used to be column <tt>0</tt> is now column <tt>columns()-1</tt>, ...,
     * what used to be column <tt>columns()-1</tt> is now column <tt>0</tt>. The
     * returned view is backed by this matrix, so changes in the returned view
     * are reflected in this matrix, and vice-versa.
     * <p>
     * <b>Example:</b>
     * <table border="0">
     * <tr nowrap>
     * <td valign="top">2 x 3 matrix: <br>
     * 1, 2, 3<br>
     * 4, 5, 6</td>
     * <td>columnFlip ==></td>
     * <td valign="top">2 x 3 matrix:<br>
     * 3, 2, 1 <br>
     * 6, 5, 4</td>
     * <td>columnFlip ==></td>
     * <td valign="top">2 x 3 matrix: <br>
     * 1, 2, 3<br>
     * 4, 5, 6</td>
     * </tr>
     * </table>
     * 
     * @return a new flip view.
     * @see #viewRowFlip()
     */
    @Override
    public IntMatrix2D viewColumnFlip() {
        if (columns == 0)
            return this;
        IntMatrix2D view = new WrapperIntMatrix2D(this) {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public int getQuick(int row, int column) {
                return content.get(row, columns - 1 - column);
            }

            @Override
            public void setQuick(int row, int column, int value) {
                content.set(row, columns - 1 - column, value);
            }
        };
        return view;
    }

    /**
     * Constructs and returns a new <i>dice (transposition) view</i>; Swaps
     * axes; example: 3 x 4 matrix --> 4 x 3 matrix. The view has both
     * dimensions exchanged; what used to be columns become rows, what used to
     * be rows become columns. In other words:
     * <tt>view.get(row,column)==this.get(column,row)</tt>. This is a zero-copy
     * transposition, taking O(1), i.e. constant time. The returned view is
     * backed by this matrix, so changes in the returned view are reflected in
     * this matrix, and vice-versa. Use idioms like
     * <tt>result = viewDice(A).copy()</tt> to generate an independent
     * transposed matrix.
     * <p>
     * <b>Example:</b>
     * <table border="0">
     * <tr nowrap>
     * <td valign="top">2 x 3 matrix: <br>
     * 1, 2, 3<br>
     * 4, 5, 6</td>
     * <td>transpose ==></td>
     * <td valign="top">3 x 2 matrix:<br>
     * 1, 4 <br>
     * 2, 5 <br>
     * 3, 6</td>
     * <td>transpose ==></td>
     * <td valign="top">2 x 3 matrix: <br>
     * 1, 2, 3<br>
     * 4, 5, 6</td>
     * </tr>
     * </table>
     * 
     * @return a new dice view.
     */
    @Override
    public IntMatrix2D viewDice() {
        WrapperIntMatrix2D view = new WrapperIntMatrix2D(this) {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public int getQuick(int row, int column) {
                return content.get(column, row);
            }

            @Override
            public void setQuick(int row, int column, int value) {
                content.set(column, row, value);
            }
        };
        view.setNrows(columns);
        view.setNcolumns(rows);
        return view;
    }

    /**
     * Sets the number of rows of this matrix
     * 
     * @param columns
     */
    public void setNcolumns(int columns) {
        this.columns = columns;
    }

    /**
     * Sets the number of rows of this matrix
     * 
     * @param rows
     */
    public void setNrows(int rows) {
        this.rows = rows;
    }

    /**
     * Constructs and returns a new <i>sub-range view</i> that is a
     * <tt>height x width</tt> sub matrix starting at <tt>[row,column]</tt>.
     * 
     * Operations on the returned view can only be applied to the restricted
     * range. Any attempt to access coordinates not contained in the view will
     * throw an <tt>IndexOutOfBoundsException</tt>.
     * <p>
     * <b>Note that the view is really just a range restriction:</b> The
     * returned matrix is backed by this matrix, so changes in the returned
     * matrix are reflected in this matrix, and vice-versa.
     * <p>
     * The view contains the cells from <tt>[row,column]</tt> to
     * <tt>[row+height-1,column+width-1]</tt>, all inclusive. and has
     * <tt>view.rows() == height; view.columns() == width;</tt>. A view's legal
     * coordinates are again zero based, as usual. In other words, legal
     * coordinates of the view range from <tt>[0,0]</tt> to
     * <tt>[view.rows()-1==height-1,view.columns()-1==width-1]</tt>. As usual,
     * any attempt to access a cell at a coordinate
     * <tt>column&lt;0 || column&gt;=view.columns() || row&lt;0 || row&gt;=view.rows()</tt>
     * will throw an <tt>IndexOutOfBoundsException</tt>.
     * 
     * @param row
     *            The index of the row-coordinate.
     * @param column
     *            The index of the column-coordinate.
     * @param height
     *            The height of the box.
     * @param width
     *            The width of the box.
     * @throws IndexOutOfBoundsException
     *             if
     *             <tt>column<0 || width<0 || column+width>columns() || row<0 || height<0 || row+height>rows()</tt>
     * @return the new view.
     * 
     */
    @Override
    public IntMatrix2D viewPart(final int row, final int column, int height, int width) {
        checkBox(row, column, height, width);
        WrapperIntMatrix2D view = new WrapperIntMatrix2D(this) {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public int getQuick(int i, int j) {
                return content.get(row + i, column + j);
            }

            @Override
            public void setQuick(int i, int j, int value) {
                content.set(row + i, column + j, value);
            }
        };
        view.setNrows(height);
        view.setNcolumns(width);

        return view;
    }

    /**
     * Constructs and returns a new <i>slice view</i> representing the columns
     * of the given row. The returned view is backed by this matrix, so changes
     * in the returned view are reflected in this matrix, and vice-versa. To
     * obtain a slice view on subranges, construct a sub-ranging view (
     * <tt>viewPart(...)</tt>), then apply this method to the sub-range view.
     * <p>
     * <b>Example:</b>
     * <table border="0">
     * <tr nowrap>
     * <td valign="top">2 x 3 matrix: <br>
     * 1, 2, 3<br>
     * 4, 5, 6</td>
     * <td>viewRow(0) ==></td>
     * <td valign="top">Matrix1D of size 3:<br>
     * 1, 2, 3</td>
     * </tr>
     * </table>
     * 
     * @param row
     *            the row to fix.
     * @return a new slice view.
     * @throws IndexOutOfBoundsException
     *             if <tt>row < 0 || row >= rows()</tt>.
     * @see #viewColumn(int)
     */
    @Override
    public IntMatrix1D viewRow(int row) {
        checkRow(row);
        return new DelegateIntMatrix1D(this, row);
    }

    /**
     * Constructs and returns a new <i>flip view</i> along the row axis. What
     * used to be row <tt>0</tt> is now row <tt>rows()-1</tt>, ..., what used to
     * be row <tt>rows()-1</tt> is now row <tt>0</tt>. The returned view is
     * backed by this matrix, so changes in the returned view are reflected in
     * this matrix, and vice-versa.
     * <p>
     * <b>Example:</b>
     * <table border="0">
     * <tr nowrap>
     * <td valign="top">2 x 3 matrix: <br>
     * 1, 2, 3<br>
     * 4, 5, 6</td>
     * <td>rowFlip ==></td>
     * <td valign="top">2 x 3 matrix:<br>
     * 4, 5, 6 <br>
     * 1, 2, 3</td>
     * <td>rowFlip ==></td>
     * <td valign="top">2 x 3 matrix: <br>
     * 1, 2, 3<br>
     * 4, 5, 6</td>
     * </tr>
     * </table>
     * 
     * @return a new flip view.
     * @see #viewColumnFlip()
     */
    @Override
    public IntMatrix2D viewRowFlip() {
        if (rows == 0)
            return this;
        IntMatrix2D view = new WrapperIntMatrix2D(this) {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public int getQuick(int row, int column) {
                return content.get(rows - 1 - row, column);
            }

            @Override
            public void setQuick(int row, int column, int value) {
                content.set(rows - 1 - row, column, value);
            }
        };
        return view;
    }

    /**
     * Constructs and returns a new <i>selection view</i> that is a matrix
     * holding the indicated cells. There holds
     * <tt>view.rows() == rowIndexes.length, view.columns() == columnIndexes.length</tt>
     * and <tt>view.get(i,j) == this.get(rowIndexes[i],columnIndexes[j])</tt>.
     * Indexes can occur multiple times and can be in arbitrary order.
     * <p>
     * <b>Example:</b>
     * 
     * <pre>
     * 	 this = 2 x 3 matrix:
     * 	 1, 2, 3
     * 	 4, 5, 6
     * 	 rowIndexes     = (0,1)
     * 	 columnIndexes  = (1,0,1,0)
     * 	 --&gt;
     * 	 view = 2 x 4 matrix:
     * 	 2, 1, 2, 1
     * 	 5, 4, 5, 4
     * 
     * </pre>
     * 
     * Note that modifying the index arguments after this call has returned has
     * no effect on the view. The returned view is backed by this matrix, so
     * changes in the returned view are reflected in this matrix, and
     * vice-versa.
     * <p>
     * To indicate "all" rows or "all columns", simply set the respective
     * parameter
     * 
     * @param rowIndexes
     *            The rows of the cells that shall be visible in the new view.
     *            To indicate that <i>all</i> rows shall be visible, simply set
     *            this parameter to <tt>null</tt>.
     * @param columnIndexes
     *            The columns of the cells that shall be visible in the new
     *            view. To indicate that <i>all</i> columns shall be visible,
     *            simply set this parameter to <tt>null</tt>.
     * @return the new view.
     * @throws IndexOutOfBoundsException
     *             if <tt>!(0 <= rowIndexes[i] < rows())</tt> for any
     *             <tt>i=0..rowIndexes.length()-1</tt>.
     * @throws IndexOutOfBoundsException
     *             if <tt>!(0 <= columnIndexes[i] < columns())</tt> for any
     *             <tt>i=0..columnIndexes.length()-1</tt>.
     */
    @Override
    public IntMatrix2D viewSelection(int[] rowIndexes, int[] columnIndexes) {
        // check for "all"
        if (rowIndexes == null) {
            rowIndexes = new int[rows];
            for (int i = rows; --i >= 0;)
                rowIndexes[i] = i;
        }
        if (columnIndexes == null) {
            columnIndexes = new int[columns];
            for (int i = columns; --i >= 0;)
                columnIndexes[i] = i;
        }

        checkRowIndexes(rowIndexes);
        checkColumnIndexes(columnIndexes);
        final int[] rix = rowIndexes;
        final int[] cix = columnIndexes;

        WrapperIntMatrix2D view = new WrapperIntMatrix2D(this) {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public int getQuick(int i, int j) {
                return content.get(rix[i], cix[j]);
            }

            @Override
            public void setQuick(int i, int j, int value) {
                content.set(rix[i], cix[j], value);
            }
        };
        view.setNrows(rowIndexes.length);
        view.setNcolumns(columnIndexes.length);

        return view;
    }

    /**
     * Constructs and returns a new <i>stride view</i> which is a sub matrix
     * consisting of every i-th cell. More specifically, the view has
     * <tt>this.rows()/rowStride</tt> rows and
     * <tt>this.columns()/columnStride</tt> columns holding cells
     * <tt>this.get(i*rowStride,j*columnStride)</tt> for all
     * <tt>i = 0..rows()/rowStride - 1, j = 0..columns()/columnStride - 1</tt>.
     * The returned view is backed by this matrix, so changes in the returned
     * view are reflected in this matrix, and vice-versa.
     * 
     * @param _rowStride
     *            the row step factor.
     * @param _columnStride
     *            the column step factor.
     * @return a new view.
     * @throws IndexOutOfBoundsException
     *             if <tt>rowStride<=0 || columnStride<=0</tt>.
     */
    @Override
    public IntMatrix2D viewStrides(final int _rowStride, final int _columnStride) {
        if (_rowStride <= 0 || _columnStride <= 0)
            throw new IndexOutOfBoundsException("illegal stride");
        WrapperIntMatrix2D view = new WrapperIntMatrix2D(this) {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public int getQuick(int row, int column) {
                return content.get(_rowStride * row, _columnStride * column);
            }

            @Override
            public void setQuick(int row, int column, int value) {
                content.set(_rowStride * row, _columnStride * column, value);
            }
        };
        view.setNrows(rows);
        view.setNcolumns(columns);
        if (rows != 0)
            view.setNrows((rows - 1) / _rowStride + 1);
        if (columns != 0)
            view.setNcolumns((columns - 1) / _columnStride + 1);
        return view;
    }

    /**
     * Returns the content of this matrix if it is a wrapper; or <tt>this</tt>
     * otherwise. Override this method in wrappers.
     */
    @Override
    protected IntMatrix2D getContent() {
        return content;
    }

    /**
     * Construct and returns a new 1-d matrix <i>of the corresponding dynamic
     * type</i>, sharing the same cells. For example, if the receiver is an
     * instance of type <tt>DenseIntMatrix2D</tt> the new matrix must be of type
     * <tt>DenseIntMatrix1D</tt>, if the receiver is an instance of type
     * <tt>SparseIntMatrix2D</tt> the new matrix must be of type
     * <tt>SparseIntMatrix1D</tt>, etc.
     * 
     * @param size
     *            the number of cells the matrix shall have.
     * @param offset
     *            the index of the first element.
     * @param stride
     *            the number of indexes between any two elements, i.e.
     *            <tt>index(i+1)-index(i)</tt>.
     * @return a new matrix of the corresponding dynamic type.
     */
    @Override
    protected IntMatrix1D like1D(int size, int offset, int stride) {
        throw new InternalError(); // should never get called
    }

    /**
     * Construct and returns a new selection view.
     * 
     * @param rowOffsets
     *            the offsets of the visible elements.
     * @param columnOffsets
     *            the offsets of the visible elements.
     * @return a new view.
     */
    @Override
    protected IntMatrix2D viewSelectionLike(int[] rowOffsets, int[] columnOffsets) {
        throw new InternalError(); // should never be called
    }
}