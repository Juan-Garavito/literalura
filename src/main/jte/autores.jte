@import com.reto.literalura.model.Autor
@import java.util.List

@param List<Autor> autores

<!doctype html>
<html lang="es">
<head>
    <title>Literalura</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="title" content="Literalura - Autores">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<header class="bg-blue-600 text-white p-4 shadow-md">
    <div class="container mx-auto flex flex-wrap justify-between items-center">
        <h1 class="text-2xl font-bold">Literalura</h1>
        <div class="flex items-center space-x-2 mt-2 sm:mt-0">
            <label class="label-fecha text-sm font-medium">Fecha</label>
            <input type="text" class="input-fecha bg-white text-gray-800 rounded-md px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-300 transition duration-150 ease-in-out" placeholder="YYYY">
            <button class="btn-filtrar bg-white text-blue-600 font-semibold py-2 px-4 rounded-md hover:bg-blue-100 focus:outline-none focus:ring-2 focus:ring-blue-300 transition duration-150 ease-in-out">
                Filtrar
            </button>
        </div>
        <nav class="mt-2 sm:mt-0">
            <a href="/" class="mx-2 hover:underline">Libros</a>
            <a href="autores" class="mx-2 hover:underline">Autores</a>
        </nav>
    </div>
</header>
<main class="container mx-auto mt-8 px-4">
    <h2 class="text-3xl font-bold mb-6 text-center text-gray-800">Lista de autores</h2>
    <div class="autores-grid grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
        @for(Autor autor : autores)
            <div class="bg-white rounded-lg shadow-md overflow-hidden transition-transform duration-300 hover:scale-105">
                <div class="p-4">
                    <h3 class="font-bold text-xl mb-2 text-gray-800">${autor.getNombre()}</h3>
                    <p class="text-gray-600 mb-2">
                        <span class="font-semibold">Nacimiento:</span> ${autor.getNacimiento()}
                    </p>
                    @if(autor.getFallecimiento() != null)
                        <p class="text-gray-600">
                            <span class="font-semibold">Fallecimiento:</span> ${autor.getFallecimiento()}
                        </p>
                    @else
                        <p class="text-gray-600 italic">En vida</p>
                    @endif
                </div>
            </div>
        @endfor
    </div>
</main>

@raw
<script>
    const $autoresGrid = document.querySelector(".autores-grid")
    const $btnFiltrar  = document.querySelector(".btn-filtrar")
    const $inputFecha = document.querySelector(".input-fecha")
    const $labelFecha = document.querySelector(".label-fecha")
    const regex = new RegExp("^\\d{4}$")

    $btnFiltrar.onclick = () => {
        const fecha = $inputFecha.value

        if(!regex.test(fecha)){
            $labelFecha.innerHTML = "Hay un error en la fecha"
            const timeout = setTimeout(()=>{
                $labelFecha.innerHTML = "Fecha"
                clearInterval(timeout)
            },1000)
            return;
        }

        fetch("fecha/"+fecha).then(res=>res.json()).then(res=>{
            $autoresGrid.innerHTML = ""
            res.forEach(autor => {
                $autoresGrid.innerHTML += `
                <div class="bg-white rounded-lg shadow-md overflow-hidden transition-transform duration-300 hover:scale-105">
                    <div class="p-4">
                        <h3 class="font-bold text-xl mb-2 text-gray-800">${autor.nombre}</h3>
                        <p class="text-gray-600 mb-2">
                            <span class="font-semibold">Nacimiento:</span> ${autor.nacimiento}
                        </p>
                        <p class="text-gray-600">
                            <span class="font-semibold">Fallecimiento:</span> ${autor.fallecimiento}
                        </p>
                    </div>
                </div>`
            })
        })
    }
</script>
@endraw
</body>
</html>