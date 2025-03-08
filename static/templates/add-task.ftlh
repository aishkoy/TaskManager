<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
    <title>Добавление задачи</title>
</head>
<body class="bg-gray-100 p-6">
<div class="max-w-2xl mx-auto bg-white shadow-md rounded-lg overflow-hidden">
    <div class="bg-blue-500 p-4">
        <div class="flex justify-between items-center">
            <h1 class="text-2xl font-bold text-white">Добавление задачи на ${day.day}</h1>
            <a href="/day?dayId=${day.day}" class="bg-white text-blue-500 py-2 px-4 rounded hover:bg-blue-50 transition-colors">
                ← Назад
            </a>
        </div>
    </div>

    <div class="p-6 border-b">
        <h2 class="text-xl font-semibold text-gray-800 mb-4">Добавить новую задачу</h2>
        <#include "error-success.ftlh">
        <form action="/add-task?dayId=${day.day}" method="post" class="space-y-4">
            <input type="hidden" name="dayNum" value="${day.day}">

            <div>
                <label for="title" class="block text-sm font-medium text-gray-700 mb-1">Название задачи</label>
                <input type="text" id="title" name="title"
                       class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>

            <div>
                <label for="taskType" class="block text-sm font-medium text-gray-700 mb-1">Тип задачи</label>
                <select id="type" name="taskType"
                        class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                    <option value="TASK">Обычная задача</option>
                    <option value="URGENT">Срочное дело</option>
                    <option value="WORK">Работа</option>
                    <option value="SHOPPING">Покупки</option>
                    <option value="OTHER">Прочее</option>
                </select>
            </div>

            <div>
                <label for="description" class="block text-sm font-medium text-gray-700 mb-1">Описание</label>
                <textarea id="description" name="description" rows="3"
                          class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"></textarea>
            </div>

            <div class="flex space-x-3 pt-2">
                <button type="submit"
                        class="bg-green-500 hover:bg-green-600 text-white py-2 px-4 rounded transition-colors">
                    Добавить задачу
                </button>
                <a href="/day?dayId=${day.day}"
                   class="inline-block bg-gray-300 hover:bg-gray-400 text-gray-800 py-2 px-4 rounded transition-colors">
                    Отмена
                </a>
            </div>
        </form>
    </div>
</div>
</body>
</html>