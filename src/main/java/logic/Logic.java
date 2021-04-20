package logic;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Logic {
    public List<Point> tabulate(double start, double finish, double step) {
        int n = (int)Math.round((finish-start)/step) + 1;
        return IntStream.range(0, n).mapToDouble(i->start + i * step).mapToObj(x -> new Point(x,f(x))).collect(Collectors.toList());
    }

    public double f(double x) {
        return Math.sin(x);
    }
}
