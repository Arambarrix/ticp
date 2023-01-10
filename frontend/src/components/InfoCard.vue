<script setup>
    import { computed } from 'vue'

    const props = defineProps({'image':String , 'number':Number , 'text':String, 'color':String});

    var cssVars = computed(() => {
        return {
        '--bg-color': props.color,
      }
    });


    function getImageUrl () {
        return new URL(`../assets/img/${props.image}`, import.meta.url)
    }
</script>

<template>
    <div class="flex flex-col space-y-2 text-white items-center font-bold py-4 rounded-lg divbody" :style="cssVars" >
        <div class="flex flex-row  space-x-5 items-center">
            <img :src="getImageUrl()" class="w-5 sm:w-7 md:w-10 h-auto" alt="card image" />

            <span class="text-md sm:text-xl md:text-3xl">{{number}}</span>
        </div>
        <p class="text-xs sm:text-md md:text-lg">{{text}}</p>
    </div>
</template>

<style scoped>
    .divbody {
        background-color: var(--bg-color);
    }
</style>