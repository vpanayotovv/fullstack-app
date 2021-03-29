const allCocktails = document.getElementById('albumsList')
const searchBar = document.getElementById('searchInput')

const cocktailList = [];

fetch("http://localhost:8080/cocktails/api").
then(response => response.json()).
then(data => {
    for (let cocktail of data) {
        cocktailList.push(cocktail);
    }
})

searchBar.addEventListener('keyup', (e) => {
    const searchingChars = searchBar.value.toLowerCase();
    let filterCocktails = cocktailList.filter(cocktail => {
        return cocktail.name.toLowerCase().includes(searchingChars);
    });
    displayAlbums(filterCocktails)
})

const displayAlbums = (cocktails) => {
    allCocktails.innerHTML = cocktails
        .map((c) => {
            return `<div class="row no-collapse-1">
                        <section class="4u">
                            <div class="item">
                                <a href="/cocktails/${c.id}" class="image feature">
                                    <img class="img" src="${c.imgUrl}"
                                        alt=""></a>
                                <span>${c.baseAlcohol}</span>
                                <p>${c.name}"</p>
                            </div>
                        </section>
                    </div>`
        })
        .join('');
}