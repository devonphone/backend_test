import  '../style/style.css'
import Logo from '../../assets/logo.jpg';
import Posts from '../../components/Posts';
import Pagination from '../../components/Pagination';
import axios from 'axios'
import { useEffect, useState } from 'react';

function Home (){
    const [data, setData] = useState([])
    const [loading, setLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [postsPerPage, setPostsPerPage] = useState(1);
    const [getPagination, setGetPagination] = useState([])

    useEffect(() => {
        axios.get('http://localhost:8080/api/products')
        .then(res => setData(res.data))
        .catch(err => console.log(err));

        axios.get('http://localhost:8080/api/products/pagination/0/10')
        .then(res => setPostsPerPage(res.data.response.pageable.pageSize))
        .catch(err => console.log(err));
    },[])

    // Get current posts
    const indexOfLastPost = currentPage * postsPerPage;
    const indexOfFirstPost = indexOfLastPost - postsPerPage;
    const currentPosts = data.slice(indexOfFirstPost, indexOfLastPost);

    // Change page
    const paginate = pageNumber => setCurrentPage(pageNumber);

    return(

        <div class='container'>

            <div class = 'header'>
                <div>
                    <img src={Logo} alt="" />
                </div>
                <div id='username'>
                    <h3>Hi, Budi</h3>
                    <h4>Administrator</h4>
                </div>
            </div>

            <div class='body'>
                <div class='menu'>
                    <h4>Home <br /> Product Master</h4>
                </div>

                <div class='product'>
                    <div class='obox'>
                        <h2>Product</h2>
                        
                        <div className='container mt-5'>
                            <h1 className='text-primary mb-3'>Product List</h1>
                            <Posts posts={currentPosts} loading={loading} />
                            <Pagination
                                postsPerPage={postsPerPage}
                                totalPosts={data.length}
                                paginate={paginate}
                            />
                        </div>
                    </div>
                    
                </div>

            </div>
        </div>

        
    );
}

export default Home;
