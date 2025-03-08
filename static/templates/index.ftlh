<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
    <title>TaskManager</title>
</head>

<body>
<body class="bg-gray-50 font-sans">
<div class="container mx-auto px-4 py-8">
    <h1 class="text-3xl font-bold text-center text-gray-800 mb-8">Список задач на ${month} ${year}</h1>

    <div class="max-w-4xl mx-auto bg-white rounded-lg shadow-md overflow-hidden">
        <table class="w-full border-collapse">
            <thead>
            <tr>
                <#list weekDays as day>
                    <th class="bg-blue-500 text-white py-3 px-2 text-sm font-medium">
                        ${day}
                    </th>
                </#list>
            </tr>
            </thead>
            <tbody>
            <#list weeks as week>
                <tr>
                    <#list week as day>
                        <#if day.day == 0>
                            <td class="border border-gray-200 bg-gray-100 h-24 align-top"></td>
                        <#else>

                            <td class="border border-gray-200 <#if day.today>bg-blue-100</#if> h-24 p-2 align-top transition-colors hover:bg-gray-50">
                                <a href="/day?id=${day.day}" class="block w-full h-full">
                                <div class="flex justify-between">
                                    <span class="font-semibold text-gray-700">
                                        ${day.day}
                                    </span>
                                </div>
                                </a>
                            </td>
                        </#if>
                    </#list>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
</body>
</body>

</html>