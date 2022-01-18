# 数学公式

## 分解出一个数的质因数

比如360=2 * 2 * 2 * 3 * 3 * 5

```java
static int[] prim = new int[100000];
	static int[] sum_pri = new int[100000];
	static int index = 0;
	
	// 分解质因数 m即m如果是10进制 那么有2和5
	// 分解25 会出现5*5 
	// 分解30 会出现5和6
	// 分解360位2*2*2*3*3*5
	public static void getpri(int m) {
		for(int i=2;i*i<=m;i++) {
			while(m%i==0) {
				prim[index]=i;
				sum_pri[index]++;
				m /= i;
			}
			// 移动到下一个索引
			if(sum_pri[index]>0) {
				index++;
			}
		}
		// 如果无法分解那么就是其本身
		if(m>1) {
			prim[index] = m;
			sum_pri[index] = 1;
			index++;
		}
		
		
	}
```

## 最小公倍数

先把两数的质因数写出来；

最小公倍数等于它们所有的质因数的乘积(如果有几个相同，则乘次数最多的)

找到最大公约数一除以就得到最小公倍数。

```java
public static int lcm(int p,int q){
    int p1 = p;
    int q1 = q;
    while(q!=0){
        int r= p%q;
        p = q;
        q = r;
    }
    return (p1*p2)/p;
}
```

## 最大公约数

辗转相除法

```java
public static int  gcd(int p,int q){
    if(q==0){
        return p;
    }
    int r=p%q;
    return gcd(q,r);
}
```

