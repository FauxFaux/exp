#include <math.h>

double go(double *doubles, int length) {
    double result = 0.0;
    for (int i = 0; i < length; ++i) {
        double d = doubles[i];
        result += 0.12093742 * (1.0 + (-1.5 * d * d + 2));
    }
    return result;
}

