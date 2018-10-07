#include <iostream>
#include <iterator>

// Vectors
#include <vector>

// List
#include <list>

// Deque
#include <deque>

// Queue
#include <queue>

// Map
#include <map>

// Unordered Map
#include <unordered_map>

// Set
#include <set>

using namespace std;

int main() {
  /*
   * Vector
   */
  vector<int> v;

  v.push_back(1);
  v.insert(v.begin(), 5);

  v.assign(7, 100); // 7 100s inserted

  v.size();

  v.at(index);
  v.front();
  v.back();

  v.pop_back();
  v.erase(v.begin());

  v.rbegin(); // Pointer to beginning of reverse vector
  v.rend();

  /*
   * List
   */
  list<int> l;

  l.push_front(1);
  l.push_back(2);

  l.front(); // 1
  l.back(); // 2

  l.pop_front();
  l.popback();

  l.reverse();

  l.sort();

  /*
   * Deque
   */

   deque<int> d;

   d.push_front(1);
   d.push_back(2);

   d.at(index);

   d.front();
   d.back();

   d.pop_front();
   d.pop_back();

   /*
    * Queue
    */

    queue<int> q;

    q.push(1);

    q.size();

    q.front();
    q.back();

    q.pop();

    /*
     * Map
     */
    map <int, int> m;

    m.insert(pair<int, int> (1, 10));
    m.insert(pair<int, int> (2, 20));
    m[3] = 30; // Alternate

    map<int, int> :: iterator i;

    for (i = m.begin(); i != m.end(); i++)
    {
        cout  <<  '\t' << i->first << '\t' << i->second << '\n';
    }

    m.erase(1); // Remove pair with key = 1
    m.clear();

    m.at(key);

    m.size();

    /*
     * Unordered Map
     */

    unordered_map<int, int> umap;

    umap[1] = 10;
    umap[2] = 20;

    umap.end();

    unordered_map<int, int>:: iterator i;

    for (itr = umap.begin(); itr != umap.end(); itr++)
    {
        cout << i->first << "  " << i->second << endl;
    }

    umap.find(key);

    /*
     * Set
     */

    set<int> s;

    s.insert(1);
    s.insert(2);

    set<int> :: iterator i;
    for (i = s.begin(); i != s.end(); i++)
    {
        cout << '\t' << *i;
    }

    s.erase(1);
    s.clear();

    s.size();

    s.find(element) != s.end(); // true if element present
}
