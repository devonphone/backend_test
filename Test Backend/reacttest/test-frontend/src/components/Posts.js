import React from 'react';

const Posts = ({ posts, loading }) => {
  if (loading) {
    return <h2>Loading...</h2>;
  }
console.log(posts)
  return (
      <table>
        <thead>
            <tr>
                <th>PRODUCT CODE</th>
                <th>PRODUCT NAME</th>
                <th>SUB CATEGORY</th>
                <th>BRAND</th>
                <th>RETAIL PRICE</th>
                <th>STATUS</th>
            </tr>
        </thead>
            <tbody>
                {posts.map(product => {
                    return (
                        <tr key={product.productCode}>
                            <td>{ product.productCode }</td>
                            <td>{ product.productName }</td>
                            <td>{ product.subCategory }</td>
                            <td>{ product.brand }</td>
                            <td>{ product.retailPrice }</td>
                            <td>{ product.status }</td>
                        </tr>
                            );
                })}
                            </tbody>
                        </table> 
  )};

export default Posts;