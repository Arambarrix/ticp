<script setup>
  const props = defineProps({'name':String, 'nb_joueur':Number});
  const colors =["bg-blue-900", "bg-green-900", "bg-purple-900","bg-dark-brown"]
</script>
<template>
    <div class="flex flex-row space-x-5 m-5 items-center">
        <button class="text-2xl text-white rounded-full w-10 h-10" :class="colors[Math.floor(Math.random() * 4)]">
            {{props.name.substring(0,1)}}
        </button>
        <div class="flex flex-col text-xs">
            <span class="overflow-hidden hover:text-clip">{{props.name}}</span>
            <div class=" flex flex-row space-x-2 items-baseline">
                <i class="fa-thin fa-user"></i>
                <span>{{props.nb_joueur}}</span>
            </div>
        </div>
    </div>
</template>