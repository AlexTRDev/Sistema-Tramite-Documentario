package Util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class mdlGeneral extends AbstractTableModel {
    protected String[] columns;
    protected List data;

    public mdlGeneral(String[] columns) {
        if(columns != null) {
            this.columns = columns;
            data = new ArrayList();
        }
    }

    @Override
    public int getColumnCount() {
        int columnCount;

        if(columns != null) {
            columnCount  = columns.length;
        }
        else {
            columnCount = 0;
        }

        return columnCount;
    }
    
    @Override
    public int getRowCount() {
        int rowCount;

        if(data != null) {
            rowCount = data.size();
        }
        else {
            rowCount = 0;
        }

        return rowCount;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String columnName;

        if(columns != null) {
            columnName = columns[columnIndex];
        }
        else {
            columnName = "";
        }

        return columnName;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }
    
    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        String valueAt;

        if(data != null) {
            if(data.size() > 0) {
                if(rowIndex >= 0 && rowIndex < data.size() && columnIndex >= 0 && columnIndex < ((Object[])(data.get(0))).length) {
                    if(((Object[])(data.get(rowIndex)))[columnIndex] != null) {
                        valueAt = ((Object[])(data.get(rowIndex)))[columnIndex].toString();
                    }
                    else {
                        valueAt =  "";
                    }
                }
                else {
                    valueAt = "";
                }
            }
            else {
                valueAt = "";
            }
        }
        else {
            valueAt = "";
        }

        return valueAt;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(data != null) {
            if(data.size() > 0) {
                if(rowIndex >= 0 && rowIndex < data.size() && columnIndex >= 0 && columnIndex < ((Object[])(data.get(0))).length) {
                    if(aValue != null) {
                        ((Object[])(data.get(rowIndex)))[columnIndex] = aValue;
                    }
                    else {
                        ((Object[])(data.get(rowIndex)))[columnIndex] = "";
                    }

                    fireTableCellUpdated(rowIndex,columnIndex);
                }
            }
        }
    }

    public void setData(List newdata) {
        Boolean estructuraCorrecta;

        if(columns != null) {
            if(newdata != null) {
                estructuraCorrecta = true;

                for (int i = 0; i < newdata.size(); i++) {
                    if(((Object[])(newdata.get(i))).length != columns.length) {
                        estructuraCorrecta = false;

                        break;
                    }
                }

                if(estructuraCorrecta) {
                    data = newdata;

                    fireTableDataChanged();
                }
            }
        }
    }

    public void addData(Object[] newdata) {
        Boolean encontrado;

        if(columns != null) {
            if(data != null) {
                if(newdata != null) {
                    if(newdata.length == columns.length) {
                        encontrado = false;

                        for (int i = 0; i < data.size(); i++) {
                            if(newdata[0].toString().equals(((Object[])(data.get(i)))[0].toString())) {
                                encontrado = true;
                            }
                        }

                        if(!encontrado) {
                            data.add(newdata);

                            fireTableDataChanged();
                        }
                    }
                }
            }
        }
    }

    public void addData(List newdata) {
        if(newdata != null) {
            for (int i = 0; i < newdata.size(); i++) {
                addData((Object[])(newdata.get(i)));
            }
        }
    }

    public void removeData(int rowIndex) {
        if(data != null) {
            if(data.size() > 0) {
                if(rowIndex >= 0 && rowIndex < data.size()) {
                    data.remove(rowIndex);

                    fireTableDataChanged();
                }
            }
        }
    }

    public void removeData() {
        if(data != null) {
            Integer numeroElementos = data.size();

            for(int i = 0; i < numeroElementos.intValue(); i++){
                removeData(0);
            }

            fireTableDataChanged();
        }
    }
}