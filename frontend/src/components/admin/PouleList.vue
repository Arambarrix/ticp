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
                                <th scope="col" class="text-sm text-center px-6 py-3 text-left w-1/6">
                                    RANG
                                </th>
                                <th scope="col" class="text-sm text-center px-6 py-3 text-left w-1/6">
                                    NOM DE POULE
                                </th>
                                <th scope="col" class="text-sm text-center px-6 py-3 text-left w-1/6">
                                    1ER
                                </th>
                                <th scope="col" class="text-sm text-center px-6 py-3 text-left w-1/6">
                                    2EME
                                </th>
                                <th scope="col" class="text-sm  text-center px-6 py-3 text-left w-1/6">
                                    SCORE
                                </th>
                                <th scope="col" class="text-sm  text-center px-6 py-3 text-left w-1/6">
                                    ACTION
                                </th>
                            </tr>
                        </thead>
                        <tbody class="flex flex-col space-y-2">

                            <tr v-for="match in data" class="flex flex-row justify-between bg-gray-100  transition duration-300 ease-in-out rounded-md hover:bg-gray-200 whitespace-nowrap  overflow-hidden hover:overflow-visible hover:w-auto hover:whitespace-normal">
                                <td class="w-1/6 px-6 py-3  text-sm text-center text-gray-900">{{match.id}}</td>
                                <td class="w-1/6 px-6 py-3  text-sm text-center text-gray-900">{{match.numero}}</td>
                                <td class="w-1/6 text-sm text-gray-900 text-center font-medium px-6 py-3 overflow-hidden ">
                                    <div class="text-center p-1 rounded-xl bg-green-900 ">
                                        <span class="rounded-full px-3 text-white font-bold">{{match.equipe1}}</span>
                                    </div>
                                </td>
                                <td class="w-1/6 px-6 py-3  text-sm text-center font-medium text-gray-900">
                                    <div class="text-center p-1 rounded-xl bg-green-900 ">
                                        <span class="rounded-full px-3 text-white font-bold">{{match.equipe2}}</span>
                                    </div>
                                </td>

                                <td class="w-1/6 px-6 py-3 text-center">
                                    <span class="text-gray-900 px-3">{{match.score1 + " - " + match.score2}}</span>
                                </td>

                                <td class="w-1/6 text-sm text-white font-light px-6 py-3 text-center">
                                    <div class="text-center p-1 bg-blue-900">
                                        <i class="fa-thin fa-pen text-white cursor-pointer font-bold" @click="toggleModal" title="modifier"></i><span class="text-white font-bold">  modifier</span>
                                    </div>
                                </td>

                            </tr>
                            
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>