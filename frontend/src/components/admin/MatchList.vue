<script setup>
    import { ref } from 'vue'
    const props = defineProps({'data':Object});


</script>

<template>
    <div class="flex flex-col">
        <div class="overflow-x-auto ">
            <div class="py-2 inline-block min-w-full ">
                <div class="overflow-hidden">
                    <table class="w-full flex flex-col table-auto	">
                        <thead class="bg-green-700 text-white font-bold uppercase rounded-md">
                            <tr class="flex flex-row justify-between ">
                                <th scope="col" class="text-sm text-center px-6 py-3 w-1/8">
                                    N°
                                </th>
                                <th scope="col" class="text-sm text-center px-6 py-3 w-1/8">
                                    DEBUT TOURNOI
                                </th>
                                <th scope="col" class="text-sm text-center px-6 py-3 w-1/8">
                                    FIN TOURNOI
                                </th>
                                <th scope="col" class="text-sm  text-center px-6 py-3 w-1/8">
                                    POULE
                                </th>
                                <th scope="col" class="text-sm  text-center px-6 py-3 w-1/8">
                                    TABLEAU
                                </th>
                                <th scope="col" class="text-sm  text-center px-6 py-3 w-1/8">
                                    EQUIPE
                                </th>
                                <th scope="col" class="text-sm  text-center  px-6 py-3 w-1/8">
                                    MATCH
                                </th>
                                <th scope="col" class="text-sm  text-center  px-6 py-3 w-1/8">
                                    PHOTO
                                </th>
                            </tr>
                        </thead>
                        <tbody class="flex flex-col space-y-2">

                            <tr v-for="match in data" class="flex flex-row justify-between bg-gray-100  transition duration-300 ease-in-out rounded-md hover:bg-gray-200 whitespace-nowrap  overflow-hidden hover:overflow-visible hover:w-auto hover:whitespace-normal">
                                <td class="w-1/8 px-6 py-3  text-sm text-center text-gray-900">{{match.id}}</td>
                                <td class="w-1/8 px-6 py-3  text-sm text-center text-gray-900">{{match.date}}</td>
                                <td class="w-1/8 px-6 py-3  text-sm text-center text-gray-900">{{match.date}}</td>
                                <td class="w-1/8 px-6 py-3  text-sm text-center text-gray-900">{{match.valeur}}</td>
                                <td class="w-1/8 px-6 py-3  text-sm text-center text-gray-900">{{match.valeur}}</td>
                                <td class="w-1/8 px-6 py-3  text-sm text-center text-gray-900">{{match.valeur}}</td>
                                <td class="w-1/8 px-6 py-3  text-sm text-center text-gray-900">{{match.valeur}}</td>
                                <td class="w-1/8 px-6 py-3  text-sm text-center text-gray-900">{{match.valeur}}</td>
                            </tr>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>