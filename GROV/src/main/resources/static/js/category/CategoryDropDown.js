const userAction=async ()=>{
	const response = await fetch('http://localhost:9090/categories');
	const myJson = await response.json();
	return myJson;
} 

  
  const getDropDown=async ()=>{
	
	  const root_categories=document.getElementById('root_categories');
	  out();
	  let myJson=await userAction();
	  let a;
	  
	  for(let i=0;i<myJson.length;i++){
		  li = document.createElement('li');

		  a=document.createElement('a');
		  a.text=myJson[i]['name'];
		  a.value=myJson[i]['id'];
		  a.setAttribute("name",myJson[i]['name'])
		  a.setAttribute("id",myJson[i]['name'])
		  a.className="anchor"
		  //a.href="http://localhost:9090/ProductsListing.html"
		  li.setAttribute("id","root_category")
		  li.setAttribute("onmouseover","subCategories("+myJson[i]['id']+")")
		  li.setAttribute("onClick", "redirectToRootProductListing("+myJson[i]['id']+")")
		 
		  li.appendChild(a)
		  root_categories.appendChild(li);
		  //
	  } 
  };
  
  function redirectToRootProductListing(id){
	window.location.href = "http://localhost:9090/product/products_listing_page.html?rootCatId="+id;
}
  function redirectToProductListing(id){
	window.location.href = "http://localhost:9090/product/products_listing_page.html?catId="+id;
}
  
  const subCategories=async (root_category_id)=>{
	  document.getElementById("sub_categories").style.display="inline";
	  let myJson=await userAction();
	  const sub_categories=document.getElementById('sub_categories');
	  while(sub_categories.hasChildNodes()){
		   sub_categories.removeChild(sub_categories.firstChild);
	  }
	  let root_categories=document.getElementById('sub_categories');
	  let a;
	  for(let i=0;i<myJson.length;i++){
		  if(myJson[i]['id']==root_category_id){
			  for(let j=0;j<myJson[i]['categories'].length;j++){
				  li = document.createElement('li');
				  a=document.createElement('a');
				  a.text=myJson[i]['categories'][j]['name'];
				  a.value=myJson[i]['categories'][j]['id'];
				  a.setAttribute("name",myJson[i]['categories'][j]['name'])
				  a.setAttribute("id",myJson[i]['categories'][j]['name'])
				  //a.href="http://localhost:9090/ProductsListing.html"
				  li.setAttribute("onClick","redirectToProductListing("+myJson[i]['categories'][j]['id']+ ")")
				  a.className="sub_category"
				  li.setAttribute("id","sub_category")
				  li.appendChild(a)
				  root_categories.appendChild(li);
			  }
		  break;
	  }
	} 
  };
 
  const out=()=>{
	  document.getElementById("sub_categories").style.display="none";
	  const sub_categories=document.getElementById('sub_categories');
	  while(sub_categories.hasChildNodes()){
		  sub_categories.removeChild(sub_categories.firstChild);
	  }
	  const root_categories=document.getElementById('root_categories');
	  while(root_categories.hasChildNodes()){
		  root_categories.removeChild(root_categories.firstChild);
	  }
  }
