The time complexity of the merge function would be O(N) because the function is recursively 
calling itself N times
and space complexity of the merge function would be O(N) 
as well because it is storing O(N) because it is keeping track of N items.
 The time complexity of the sort function would be Nlog(N) due to the calling mechanism. Merge would be called O(N) times and the
sort function would be halving the number of items going through it therefore being Log(N) and space complexity of the sort function
would be the same due to it being a copy of the lengths and the reason stated above.
The time complexity of the merge_in_place function O(N) because the function is recursively
calling itself N times and space complexity of the merge_in_place function would be O(N)
as well because it is storing O(N) because it is keeping track of N items.
The time complexity of the sort_in_place function Nlog(N) due to the calling mechanism. Merge would be called O(N) times and the
sort function would be halving the number of items going through it therefore being Log(N) and space complexity of your sort_in_place function
would be O(N) due the function using the list itself and not copies of the items.