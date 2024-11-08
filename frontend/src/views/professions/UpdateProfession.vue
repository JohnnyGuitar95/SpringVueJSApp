<template>
  <main>
    <Navbar />
    <div class="my-5">
      <div class="mx-auto w-50" style="max-width: 100%;">
        <h2 class="text-center mb-3">Обновить данные о профессии</h2>
        <form @submit.prevent="updateProfession">
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="name" class="form-label">Название профессии</label>
              <input id="name" type="text" name="name" class="form-control" placeholder="Название" required v-model="profession.name">
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="note" class="form-label">Примечание</label>
              <input id="note" type="text" name="note" class="form-control" placeholder="Примечание" required v-model="profession.note">
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group">
              <input class="btn btn-primary w-100" type="submit" value="Обновить">
            </div>
          </div>
          <div>
            <p v-if="message" class="text-success">{{ message }}</p>
          </div>
        </form>
      </div>
    </div>
  </main>
</template>

<script>
import Navbar from '../../components/Navbar.vue';

export default {
  name: 'UpdateProfession',
  components: {
    Navbar
  },

  data() {
    return {
      profession: {
        id: '',
        name: '',
        note: ''
      },
      message: ''
    };
  },

  beforeMount() {
    this.getProfession();
  },

  methods: {
    getProfession() {
      fetch(`http://localhost:7000/professionsApi/profession/${this.$route.params.id}`)
          .then(res => {
            if (!res.ok) {
              throw new Error('Ошибка при получении данных о профессии');
            }
            return res.json();
          })
          .then(data => {
            this.profession = data;
            console.log(this.profession);
          })
          .catch(error => console.error('Error fetching profession:', error));
    },

    updateProfession() {
      fetch(`http://localhost:7000/professionsApi/profession`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.profession)
      })
          .then(response => {
            if (response.ok) {
              this.message = 'Данные профессии успешно обновлены!';
              this.$router.push('/professionsHome');
            } else {
              throw new Error('Ошибка при обновлении профессии');
            }
          })
          .catch(error => {
            console.error('Error updating profession:', error);
            this.message = 'Произошла ошибка при обновлении профессии.';
          });
    }
  }
}
</script>