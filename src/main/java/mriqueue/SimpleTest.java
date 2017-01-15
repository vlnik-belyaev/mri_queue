package mriqueue;

/**
 * Created by vlnik on 1/14/2017.
 */
public class SimpleTest
{
    private int data;

    public SimpleTest(int data) {

        this.data = data;
    }


    public int getData() {

        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int add(int additionData)
    {
        int temp = getData() + additionData;
        setData(temp);
        return temp;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleTest that = (SimpleTest) o;

        return data == that.data;
    }

    @Override
    public int hashCode() {
        return data;
    }

    @Override
    public String toString() {
        return "SimpleTest{" +
                "data=" + data +
                '}';
    }
}
