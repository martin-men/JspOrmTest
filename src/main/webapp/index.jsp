<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Calculadora interes</title>
</head>
<body>

<div>

    <div>
        <h2>Calculadora Interés Compuesto </h2>

        <h3>${error}</h3>
        <form action="/" method="post">

            <div>

                <label for = "capital">Capital: </label>
                <input id = "capital" type="number" name="capital" value="${capital}">

                <label for = "interes">Porcentaje de interés: </label>
                <input id = "interes" type="number" min="1" max="100" value="${interes}" name="interes">

                <label for = "anios"># Años: </label>
                <input id = "anios" type="number" name="anios" value="${anios}">

                <label for = "periodos">Periodos por año: </label>
                <input id = "periodos" type="number" min="1" max="12" name="periodos" value="${periodos}">

            </div>

            <button type="submit">Calcular</button>

            <p>Resultado: ${result}</p>
            <p>History:</p>
            <p>${history}</p>

        </form>
    </div>
</div>

</body>
</html>