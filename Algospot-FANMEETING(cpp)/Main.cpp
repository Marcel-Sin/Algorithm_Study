#include <iostream>
#include <cstring>
#include <algorithm>
#include <vector>

using namespace std;
vector<int> A;
vector<int> B;
vector<int> ANS;
int TOTAL;

int Solve();
void Display(vector<int>& a);
vector<int> Mul(vector<int>& a, vector<int>& b);
void Add(vector<int>& a, vector<int>& b, int k);
void Sub(vector<int>& a, vector<int>& b);
vector<int> Karatsuba(vector<int>& a, vector<int>& b);
int Min(int a, int b);
int Max(int a, int b);

int main() {
	cin.tie(NULL);
	cin.sync_with_stdio(false);
	cin >> TOTAL;

	for (int i = 0; i < TOTAL; i++) {
		cout << Solve() << "\n";
	}
}

int Solve() {
	string a,b;
	cin >> a;
	cin >> b;

	A.clear();
	B.clear();
	ANS.clear();
	int member = a.length(), fan = b.length(), counter = 0;
	for (int i = 0; i < a.length(); i++) A.push_back( (a[i] == 'F') ? 0 : 1);
	for (int i = b.length() - 1; i >= 0; i--) B.push_back( (b[i] == 'F') ? 0 : 1 );

	ANS = Karatsuba(A,B);
	for (int i = member - 1; i < fan; i++) if (ANS[i] == 0) counter++;
	return counter;
}

void Display(vector<int>& a) {
	for (int i = a.size()-1; i >= 0; i--) {
		cout << a[i] << " ";
	}
}

vector<int> Mul(vector<int>& a, vector<int>& b) {
	int sizeA = a.size(), sizeB = b.size();
	vector<int> ret(sizeA+sizeB);
	for (int i = 0; i < sizeA; i++) {
		for (int j = 0; j < sizeB; j++) {
			ret[i + j] += (a[i] * b[j]);
		}
	}
	return ret;
}
void Add(vector<int>& a, vector<int>& b, int k) {
	a.resize(Max(a.size(), b.size() + k));
	for (int i = 0; i < b.size(); i++) a[i + k] += b[i];
}
void Sub(vector<int>& a, vector<int>& b) {
	for (int i = 0; i < b.size(); i++) a[i] -= b[i];
}
vector<int> Karatsuba(vector<int>& a, vector<int>& b) {
	if (a.size() < b.size()) return Karatsuba(b, a);
	if (a.size() == 0 || b.size() == 0) {
		vector<int> empty;
		return empty;
	}
	if (a.size() <= 50) return Mul(a, b);

	int mid = a.size() / 2;
	vector<int> a0(a.begin(),a.begin()+mid);
	vector<int> a1(a.begin() + mid, a.end());

	int midB = Min(mid, b.size());
	vector<int> b0(b.begin(), b.begin() + midB);
	vector<int> b1(b.begin() + midB, b.end());

	vector<int> z0 = Karatsuba(a0, b0);
	vector<int> z2 = Karatsuba(a1, b1);

	Add( a0, a1, 0); Add( b0, b1, 0);
	vector<int> z1 = Karatsuba(a0, b0);

	Sub(z1, z0);
	Sub(z1, z2);

	vector<int> ret;
	Add(ret, z2, mid * 2);
	Add(ret, z1, mid);
	Add(ret, z0, 0);
	return ret;
}

int Min(int a, int b) {
	return (a > b) ? b : a;
}
int Max(int a, int b) {
	return (a > b) ? a : b;
}


